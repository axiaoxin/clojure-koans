(ns koans.20-java-interop
  (:require [koan-engine.core :refer :all]))

(meditations
  "You may have done more with Java than you know"
  (= java.lang.String (class "warfare")) ; hint: try typing (javadoc "warfare") in the REPL

  "The dot signifies easy and direct Java interoperation"
  (= "SELECT * FROM" (.toUpperCase "select * from"))

  "But instance method calls are very different from normal functions"
  (= ["SELECT" "FROM" "WHERE"] (map #(.toUpperCase %) ["select" "from" "where"]))

  "Constructing might be harder than breaking"
  (= 10 (let [latch (java.util.concurrent.CountDownLatch. 10)]
          (.getCount latch)))

  "Static methods are slashing prices!"
  (== 1024 (Math/pow 2 10)))

;;; 以下使用方法等价
; 构造函数
; (new Random)
; (Random.)
; 静态方法与成员
; (. Math PI)
; (Math/PI)
; 实例方法
; (. rnd nextInt)
; (.nextInt rnd)
; 对象嵌套
; (.getLocation (.getCodeSource (.getProtectionDomain (.getClass '(1 2)))))
; (.. '(1 2) getClass getProtectionDomain getCodeSource getLocation)
; 同一实例多次调用
; (doto (System/getProperties)
;    (setProperty "name" "Stuart")
;    (setProperty "favoriteColor" "blue")
; )
