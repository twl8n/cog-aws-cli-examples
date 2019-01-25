(ns api_samples.s3
  (:require [clojure.java.io :as io]
            [cognitect.aws.client.api :as aws])
  (:gen-class))

;; You must have a client
(def s3 (aws/client {:api :s3}))

;; Cognitect says: "guard against invalid :request map"
;; Not sure what this is, but an invalid request map sounds bad.
(aws/validate-requests s3 true)

;; list all my buckets
(defn list-my-buckets []
  (aws/invoke s3 {:op :ListBuckets}))

(defn basename [fullpath]
  fullpath)

(defn put-file [bucketname filename]
  (aws/invoke s3 {:op :PutObject 
                  :request {:Bucket bucketname
                            :Key filename
                            :Body (io/input-stream (.getBytes (slurp filename)))}}))
  
(defn -main []
  (let [first-bucket (:Name (first (:Buckets (list-my-buckets))))]
    (prn "first-bucket:" first-bucket)
    (put-file first-bucket "demo.jpg")
    ;; This example has no prefix for the bucket, and will do a recursive list of objects.
    (mapv #(prn (:Size %) (:Key %))
         (:Contents (aws/invoke s3 {:op :ListObjects
                                    :request {:Bucket first-bucket}})))))
