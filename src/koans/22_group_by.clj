(ns koans.22-group-by
  (:require [koan-engine.core :refer :all]))

;;; group-by demo
; ;; group strings by their length
; (group-by count ["a" "as" "asd" "aa" "asdf" "qwer"])
; ;;=> {1 ["a"], 2 ["as" "aa"], 3 ["asd"], 4 ["asdf" "qwer"]}

; ;; group integers by a predicate
; (group-by odd? (range 10))
; ;;=> {false [0 2 4 6 8], true [1 3 5 7 9]}

; ;; group by a primary key
; (group-by :user-id [{:user-id 1 :uri "/"}
;                     {:user-id 2 :uri "/foo"}
;                     {:user-id 1 :uri "/account"}])

; ;;=> {1 [{:user-id 1, :uri "/"}
; ;;       {:user-id 1, :uri "/account"}],
; ;;    2 [{:user-id 2, :uri "/foo"}]}

(defn get-odds-and-evens [coll]
  (let [{odds true evens false} (group-by __ coll)]
    [odds evens]))

(meditations
  "To categorize a collection by some function, use group-by."
  (= __ (group-by count ["hello" "world" "foo" "bar"]))

  "You can simulate filter + remove in one pass"
  (= (get-odds-and-evens [1 2 3 4 5])
     ((juxt filter remove) odd? [1 2 3 4 5])
     [[1 3 5] [2 4]])

  "You can also group by a primary key"
  (= __
     (group-by :id [{:id 1 :name "Bob"}
                    {:id 2 :name "Jennifer"}
                    {:id 1 :last-name "Smith"} ]))

  "But be careful when you group by a non-required key"
  (= {"Bob" [{:name "Bob" :id 1}]
      "Jennifer" [{:name "Jennifer" :id 2}]
      __ [{:last-name "Smith" :id 1}]}
   (group-by :name [{:id 1 :name "Bob"}
                    {:id 2 :name "Jennifer"}
                    {:id 1 :last-name "Smith"}]))

  "The true power of group-by comes with custom functions"
  (= __
     (group-by #(if (:bad %) :naughty-list :nice-list)
               [{:name "Jimmy" :bad true}
                {:name "Jane" :bad false}
                {:name "Joe" :bad true}])))
