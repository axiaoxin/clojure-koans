(ns koans.06-maps
  (:require [koan-engine.core :refer :all]))

(meditations
  "Don't get lost when creating a map"
  (= {:a 1 :b 2} (hash-map :a 1 :b 2))

  "A value must be supplied for each key"
  (= {:a 1} (hash-map :a 1))

  "The size is the number of entries"
  (= 2 (count {:a 1 :b 2}))

  "You can look up the value for a given key"
  (= 2 (get {:a 1 :b 2} :b))

  "Maps can be used as functions to do lookups"
  (= 1 ({:a 1 :b 2} :a))

  "And so can keywords"
  (= 1 (:a {:a 1 :b 2}))

  "But map keys need not be keywords"
  (= "Sochi" ({2010 "Vancouver" 2014 "Sochi" 2018 "PyeongChang"} 2014))
  ; 2014 must at last

  "You may not be able to find an entry for a key"
  (= nil (get {:a 1 :b 2} :c))

  "But you can provide your own default"
  (= :key-not-found (get {:a 1 :b 2} :c :key-not-found))

  "You can find out if a key is present"
  (= true (contains? {:a nil :b nil} :b))

  "Or if it is missing"
  (= false (contains? {:a nil :b nil} :c))

  "Maps are immutable, but you can create a new and improved version"
  (= {1 "January" 2 "February"} (assoc {1 "January"} 2 "February"))
  ; assoc 表示 associate 的意思。
  ; assoc 接受一个 Map ，还有一个或多个 key-val 对， 返回一个和传入 Map 类型相同的新 Map ， 除了原来传入 Map 已有的数据外， 新 Map 还包含传给 assoc 的那些 key-val 对。
  ; 当一个向量被应用到 assoc 函数时， 返回一个新向量， 新向量的索引下标（index） key 的值就是 val 。
  ; 注意索引下标必须 <= (count vector) 。
  ; user=> (assoc {} :Clojure "Rich")
  ; {:Clojure "Rich"}
  ; user=> (assoc {:Clojure "Rich"} :Clojure "Rich Hickey")     ; 如果有同名 key ，那么那么覆盖它
  ; {:Clojure "Rich Hickey"}
  ; user=> (assoc [1 2 3] 0 10086)
  ; [10086 2 3]
  ; user=> (assoc [1 2 3] 3 10086)          ; key 最大可以等于 (count vector)
  ; [1 2 3 10086]
  ; user=> (assoc [1 2 3] 10086 10086)      ; key 不能大于 (count vector)
  ; IndexOutOfBoundsException   clojure.lang.PersistentVector.assocN
  ; (PersistentVector.java:136)

  "You can also create a new version with an entry removed"
  (= {1 "January"} (dissoc {1 "January" 2 "February"} 2))

  "Often you will need to get the keys, but the order is undependable"
  (= (list 2010 2014 2018)
     (sort (keys { 2014 "Sochi" 2018 "PyeongChang" 2010 "Vancouver"})))

  "You can get the values in a similar way"
  (= (list "PyeongChang" "Sochi" "Vancouver")
     (sort (vals {2010 "Vancouver" 2014 "Sochi" 2018 "PyeongChang"}))))
