curl -O https://download.clojure.org/install/linux-install-1.10.0.411.sh
chmod +x linux-install-1.10.0.411.sh
sudo ./linux-install-1.10.0.411.sh

A simple S3 example is in src/api_samples/s3.clj.

Run the simple example using a minimal deps.edn file (deps.edn.s3):
`clojure -Sdeps deps.edn.s3 -m api_samples.s3`

Launch a repl (using the standard deps.edn):
`clj -A:nrepl`

Error: 
java.io.FileNotFoundException: Could not locate cognitect/aws/client/api__init.class, cognitect/aws/client/api.clj or cognitect/aws/client/api.cljc on classpath.

Solution:

The deps.edn at https://github.com/cognitect-labs/aws-api is incomplete. Heaven only knows why. The README
notes that additional deps are required.

Your deps.edn must have at least 2 deps from cognitect. 
```
com.cognitect.aws/api       {:mvn/version "0.8.204"}
com.cognitect.aws/endpoints {:mvn/version "1.1.11.481"}
```

and you will always have at least one more dep for whatever AWS system. If you are using S3 you'll need:
```
com.cognitect.aws/s3        {:mvn/version "680.2.370.0"}
```


Error:
Exception in thread "main" java.io.FileNotFoundException: Could not locate s3__init.class, s3.clj or s3.cljc on classpath.

Error:
Exception in thread "main" java.lang.NullPointerException

Solutions:

The clojure file must be: src/api_samples/s3.clj

The namespace in s3.clj must be `(ns api_samples.s3 ...`

The main function must be `-main` as in `(defn -main [] ...`


