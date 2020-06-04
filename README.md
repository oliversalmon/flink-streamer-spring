# flink-streamer-spring

To build the docker image

`docker build -t dineshpillai/flink-streamer-spring .`

to push to docker registry so the below helm charts can get to it

`docker push dineshpillai/flink-streamer-spring`

Reactive service to read the data the at the Flink Twitter PoC has put on Mongo and stream it to the UI

Make mongo local database a replica set by running the following command:

`mongod --config /usr/local/etc/mongod.conf --fork --replSet 'rs'`

Activate the replica set "rs" through mongo shell:

`
     > rs.initiate()`



To install Mongodb replica set on the Kubernetes cluster through helm:

Note: the following steps assumes you have created a Kubernetes cluster on Digital Ocean and called it flink-mongo-cluster. Download the config file and run the following commands

`helm install  flink-mongo-release stable/mongodb --kubeconfig ~/.kube/flink-mongo-cluster-kubeconfig.yaml --set ingress.enabled=true --set replicaSet.enabled=true`
  
 Obtain the password of the Mongo installation:
 cd ~/.kube
  
`export MONGODB_ROOT_PASSWORD=$(kubectl --kubeconfig=flink-mongo-cluster-kubeconfig.yaml get secret --namespace default flink-mongo-release-mongodb -o jsonpath="{.data.mongodb-root-password}" | base64 --decode)`

kubectl --kubeconfig=flink-mongo-cluster-kubeconfig.yaml port-forward --namespace default svc/flink-mongo-release-mongodb 27017:27017 &
    mongo --host 127.0.0.1 --authenticationDatabase admin -p $MONGODB_ROOT_PASSWORD
    
kubectl run --kubeconfig=flink-mongo-cluster-kubeconfig.yaml --namespace default flink-mongo-release-mongodb-client --rm --tty -i --restart='Never' --image docker.io/bitnami/mongodb:4.2.4-debian-10-r0 --command -- mongo admin --host flink-mongo-release-mongodb --authenticationDatabase admin -u root -p $MONGODB_ROOT_PASSWORD

You will need to obtain the Mongo password before by running the export MONGODB_ROOT_PASSWORD and replacing the password below
`helm install flink-streamer-spring  --kubeconfig ~/.kube/flink-mongo-cluster-kubeconfig.yaml ./flink-streamer-spring/ --set environment.profile=prod  --set environment.mongo_password=CUwFZvRH01`

To port forward using kube proxy run the following command

`kubectl --kubeconfig=flink-mongo-cluster-kubeconfig.yaml port-forward <POD NAME> 8082:8082'

NOTE: You will need to obtain the pod name by running kubectl get pods and copying the name of the flink-spring-streamer pod name

Once done, feel free to delete the release
`helm delete flink-streamer-spring  --kubeconfig ~/.kube/flink-mongo-cluster-kubeconfig.yaml`