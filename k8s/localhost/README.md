### Setting up application resources, including postgresql instance
1. first delete the persistent volume if you want a fresh db by applying the pgcleanup.yaml deployment:
   2. ed
2. Setup the dependencies: 
   1. edit the /etc/hosts file and add a new host:
      127.0.0.1      denlab.com
   2. Do the DB deployment first 
   3. Do the Kafka deployment next 
   4. Double check the cluster and verify everything started (zookeeper, kafka, postgres service/pods)
### Setting up cluster resources and Ingress-Nginx



1.  ````
    helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx && helm repo update
    ````
1. ````
   helm install ingress-nginx ingress-nginx/ingress-nginx --namespace ingress-nginx --create-namespace 
   helm install ingress-nginx ingress-nginx/ingress-nginx 
   // --set controller.publishService.enabled=true <-- didn't do this
    ````
2. Verify the Load Balancer service is assigned an external IP: 
   ````
   kubectl  get services -o wide -w ingress-nginx-controller  -n ingress-nginx
   Output
    NAME                                     TYPE           CLUSTER-IP     EXTERNAL-IP   PORT(S)                      AGE   SELECTOR
    ingress-nginx-controller   LoadBalancer   10.245.3.122   167.99.16.184   80:30953/TCP,443:30869/TCP   2m29s   ..
   ````
1. Since the apps are already exposed through the ingress resource within the deployment yaml there is nothing further to do for ingress.  The cluster is exposed on ports `80:30953/TCP,443:30869/TCP`.  However, we need to provide external access to the db resource running within the cluster and since ingress-nginx only supports load balancing http(s) and not raw TCP requests, we will need to manually configure & deploy an additional TCP Service onto the ingress controller.
2. Adding TCP Service to Ingress Controller
   1.  First, check that it's not already configured: 
      ````
      kubectl get deployment -n ingress-nginx ingress-nginx-controller -o yaml | grep tcp-services-configmap 
   ```` 
       
3. You can do it either of these ways: 
   ```` 
    helm upgrade ingress-nginx ingress-nginx/ingress-nginx \
    --namespace ingress-nginx \
    --set controller.extraArgs.tcp-services-configmap="ingress-nginx/tcp-services"
    Error: repo ingress-nginx not found
     kubectl patch deployment -n ingress-nginx ingress-nginx-controller \
    --type='json' -p='[{"op": "add", "path": "/spec/template/spec/containers/0/args/-", "value": "--tcp-services-configmap=ingress-nginx/tcp-services"}]'
   ````
The former method is dependent on the helm repo which you should have added already when you installed ingres-nginx...the latter method is a manual patching of the ingress-nginx-controller to add the tcp.services.configmap flag
4. Ensure the flag is present on the contoller: 
   ````
    kubectl get deployment -n ingress-nginx ingress-nginx-controller -o yaml | grep tcp-services-configmap
   ````
5. Add the ingress-nginx-config-map to expose TCP ports & verify:
    ```
   kubectl apply -f ingress-nginx-config-map.yaml && 
    kubectl get configmap tcp-services -n ingress-nginx -o yaml
   ```
   You shoul see a value with a data param container the following:
   `"data":{"5432":"default/postgres:5432"}`

6. Add the db host port to the nginx-controller to expose the port: 
   ```
    kubectl patch svc ingress-nginx-controller -n ingress-nginx --type='json' -p='[
    {"op": "add", "path": "/spec/ports/-", "value": {
    "name": "postgres",
    "protocol": "TCP",
    "port": 5432,
    "targetPort": 5432
    }}
    ]'
   
   Output:
   service/ingress-nginx-controller patched
     ```
6. Restart the ingress-nginx-controller: 
    ````
   
     kubectl rollout restart deployment -n ingress-nginx ingress-nginx-controller
    
    deployment.apps/ingress-nginx-controller restarted
     kubectl get svc -n ingress-nginx                                               
    NAME                                 TYPE           CLUSTER-IP       EXTERNAL-IP      PORT(S)                                     AGE
    ingress-nginx-controller             LoadBalancer   10.245.242.127   157.230.70.179   80:31093/TCP,443:32717/TCP,5432:31290/TCP   6d20h
    ingress-nginx-controller-admission   ClusterIP      10.245.196.96    <none>           443/TCP                                     6d20h
     nc -zv postgres.denlab.com 5432
    
    Connection to postgres.denilab.ca port 5432 [tcp/postgresql] succeeded!
     psql -h postgres.denlab.com -U postgres -d postgres
    psql (14.13 (Homebrew), server 13.18 (Debian 13.18-1.pgdg120+1))

   ````
5. If it all goes well, and you can hit the db server instance, run the kafka-deployment to apply kafka resources to the cluster:
   ````
   kubectl apply -f kafka-deployment.yaml 
   ````
   Check the logs on the kafka-broker pod to ensure it's started & running 
6. You're ready to deploy the application now...phew