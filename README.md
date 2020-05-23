# flink-streamer-spring
Reactive service to read the data the at the Flink Twitter PoC has put on Mongo and stream it to the UI


kubectl -f create ns.yml
kubectl -f 

helm install  flink-mongo-release stable/mongodb-replicaset --kubeconfig flink-mongo-cluster-kubeconfig.yaml
helm install  flink-mongo-release stable/mongodb --kubeconfig flink-mongo-cluster-kubeconfig.yaml --set ingress.enabled=true --set replicaSet.enabled=true
helm install ingress-release stable/nginx-ingress --kubeconfig flink-mongo-cluster-kubeconfig.yaml --set tcp={27017: "flink-demo/mongodb:27017"}

tcp:
  27017: "default/mongodb:27017"