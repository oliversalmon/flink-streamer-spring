# flink-streamer-spring
Reactive service to read the data the at the Flink Twitter PoC has put on Mongo and stream it to the UI


kubectl -f create ns.yml
kubectl -f 


helm install  flink-mongo-release stable/mongodb --kubeconfig flink-mongo-cluster-kubeconfig.yaml --set ingress.enabled=true --set replicaSet.enabled=true
  
export MONGODB_ROOT_PASSWORD=$(kubectl --kubeconfig=flink-mongo-cluster-kubeconfig.yaml get secret --namespace default flink-mongo-release-mongodb -o jsonpath="{.data.mongodb-root-password}" | base64 --decode)

kubectl --kubeconfig=flink-mongo-cluster-kubeconfig.yaml port-forward --namespace default svc/flink-mongo-release-mongodb 27017:27017 &
    mongo --host 127.0.0.1 --authenticationDatabase admin -p $MONGODB_ROOT_PASSWORD
    
kubectl run --kubeconfig=flink-mongo-cluster-kubeconfig.yaml --namespace default flink-mongo-release-mongodb-client --rm --tty -i --restart='Never' --image docker.io/bitnami/mongodb:4.2.4-debian-10-r0 --command -- mongo admin --host flink-mongo-release-mongodb --authenticationDatabase admin -u root -p $MONGODB_ROOT_PASSWORD