(ns koans.23-meta
  (:require [koan-engine.core :refer :all]))

(def giants
  (with-meta 'Giants
    {:league "National League"}))

; (with-meta obj m)
; Returns an object of the same type and value as obj, with map m as its metadata.

; (meta obj)
; Returns the metadata of obj, returns nil if there is no metadata.

(meditations
  "Some objects can be tagged using the with-meta function"
  (= {:league "National League"} (meta giants))

  "Or more succinctly with a reader macro"
  (= {:division "West"} (meta '^{:division "West"} Giants))

  "While others can't"
  (= "This doesn't implement the IObj interface"
     (try
          (with-meta
            2
            {:prime true})
          (catch ClassCastException e
            "This doesn't implement the IObj interface")))

  ; (merge {:a 1 :b 2 :c 3} {:b 9 :d 4})
  ; ;;=> {:d 4, :a 1, :b 9, :c 3}
  ; (merge {:a 1} nil)   ;=> {:a 1}
  ; (merge nil {:a 1})   ;=> {:a 1}
  ; (merge nil nil)      ;=> nil
  "Notice when metadata carries over"
  (= __ (meta (merge '^{:foo :bar} {:a 1 :b 2}
                     {:b 3 :c 4})))

  "And when it doesn't"
  (= __ (meta (merge {:a 1 :b 2}
                     '^{:foo :bar} {:b 3 :c 4})))

  "Metadata can be used as a type hint to avoid reflection during runtime"
  (= __ (#(.charAt ^String % 0) "Cast me"))

  "You can directly update an object's metadata"
  (= 8 (let [giants
             (with-meta
               'Giants
               {:world-series-titles (atom 7)})]
         (swap! (:world-series-titles (meta giants)) __)
         @(:world-series-titles (meta giants))))

  "You can also create a new object from another object with metadata"
  (= {:league "National League" :park "AT&T Park"}
     (meta (vary-meta giants
                      assoc __ __)))

  "But it won't affect behavior like equality"
  (= __ (vary-meta giants dissoc :league))

  "Or the object's printed representation"
  (= __ (pr-str (vary-meta giants dissoc :league))))
