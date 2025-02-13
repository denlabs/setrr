## **** Setup PostgreSQL in cluster with attached PVC ****
In this example, the image is pulled directly from Dockerhub when the deployment is applied.  However some additonal 
configuration is needed in order to allow external access to the db host on port 5432.  This guide should help you 
get through the installation.

### Prerequisites:
    1. A Cluster is up and running (refer to the Terraform install for an example on the Digital Ocean provider).
    2. Ingress-Nginx is applied to the cluster. Refer to the Ingress-Nginx install guide.
    3.
### 1. Download the image
 > This step is only required if you were going to install postgres via Helm: 
### 

helm install postgres bitnami/postgresql \
--set global.postgresql.postgresqlDatabase=postgres \
--set global.postgresql.postgresqlUsername=postgres \
--set global.postgresql.postgresqlPassword=born1975Feb28 \
--set service.type=LoadBalancer \
--set service.loadBalancerIP=157.230.70.179 \
--namespace default

'''
psql -h 157.230.70.179 -U postgres -d postgres
psql -h postgres.denilab.ca -U postgres -d postgres

'''

ingress-nginx-controller LoadBalancer
157.230.70.179   80:31093/TCP,443:32717/TCP 


dlabelle@denlab-mbp-689 postgres % helm install postgres oci://registry-1.docker.io/bitnamicharts/postgresql -f values.yaml


dlabelle@denlab-mbp-689 postgres % kubectl edit configmaps -n ingress-nginx ingress-nginx-controller       
data: 5432: "default/postgresql:5432"

// install ingress-nginx from helm instead?
helm upgrade --install ingress-nginx ingress-nginx \
--namespace ingress-nginx \
--set tcp.5432="default/postgresql:5432"


kubectl patch deployment ingress-nginx-controller -n ingress-nginx \
--type='json' -p='[{"op": "add", "path": "/spec/template/spec/containers/0/env/-", "value": {"name": "TCP_SERVICES_CONFIGMAP", "value": "ingress-nginx/tcp-services"}}]'