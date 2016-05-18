(ns koans.21-partition
  (:require [koan-engine.core :refer :all]))

(meditations
  "To split a collection you can use the partition function"
  (= '((0 1) (2 3)) (partition 2 (range 4)))

  "But watch out if there are not enough elements to form n sequences"
  (= '((:a :b :c)) (partition 3 [:a :b :c :d :e]))

  ;;; partition demo
  ; ;; partition a list of 20 items into 5 (20/4) lists of 4 items
  ; (partition 4 (range 20))
  ; ;;=> ((0 1 2 3) (4 5 6 7) (8 9 10 11) (12 13 14 15) (16 17 18 19))

  ; ;; partition a list of 22 items into 5 (20/4) lists of 4 items
  ; ;; the last two items do not make a complete partition and are dropped.
  ; (partition 4 (range 22))
  ; ;;=> ((0 1 2 3) (4 5 6 7) (8 9 10 11) (12 13 14 15) (16 17 18 19))

  ; ;; uses the step to select the starting point for each partition
  ; (partition 4 6 (range 20))
  ; ;;=> ((0 1 2 3) (6 7 8 9) (12 13 14 15))

  ; ;; if the step is smaller than the partition size, items will be reused
  ; (partition 4 3 (range 20))
  ; ;;=> ((0 1 2 3) (3 4 5 6) (6 7 8 9) (9 10 11 12) (12 13 14 15) (15 16 17 18))

  ; ;; when there are not enough items to fill the last partition, a pad can be supplied.
  ; (partition 3 6 ["a"] (range 20))
  ; ;;=> ((0 1 2) (6 7 8) (12 13 14) (18 19 "a"))

  ; ;; when a pad is supplied, the last partition may not be of the same size as the rest
  ; (partition 4 6 ["a"] (range 20))
  ; ;;=> ((0 1 2 3) (6 7 8 9) (12 13 14 15) (18 19 "a"))

  ; ;; but only as many pad elements are used as necessary to fill the final partition.
  ; (partition 4 6 ["a" "b" "c" "d"] (range 20))
  ; ;;=> ((0 1 2 3) (6 7 8 9) (12 13 14 15) (18 19 "a" "b"))

  ; ;; a step smaller than the partition-size results in reuse.
  ; (partition 3 1 [:a :b :c :d :e :f])
  ; ;;=> ((:a :b :c) (:b :c :d) (:c :d :e) (:d :e :f))

  ; ;; When there are less than n items in the coll, partition's behaviour
  ; ;; depends on whether there is a pad or not

  ; ;; without pad
  ; (partition 10 [1 2 3 4])
  ; ;;=> ()

  ; ;; again, without pad
  ; (partition 10 10 [1 2 3 4])
  ; ;;=> ()

  ; ;; with a pad this time (note: the pad is an empty sequence)
  ; (partition 10 10 nil [1 2 3 4])
  ; ;;=> ((1 2 3 4))

  ; ;; or, explicit empty sequence instead of nil
  ; (partition 10 10 [] [1 2 3 4])
  ; ;;=> ((1 2 3 4))


  "You can use partition-all to include any leftovers too"
  (= __ (partition-all 3 (range 5)))

  "If you need to, you can start each sequence with an offset"
  (= '((0 1 2) (5 6 7) (10 11 12)) (partition 3 __ (range 13)))

  "Consider padding the last sequence with some default values..."
  (= '((0 1 2) (3 4 5) (6 :hello)) (partition 3 3 [__] (range 7)))

  "... but notice that they will only pad up to the given sequence length"
  (= '((0 1 2) (3 4 5) __) (partition 3 3 [:these :are "my" "words"] (range 7))))
