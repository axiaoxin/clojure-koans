(ns koans.03-lists
  (:require [koan-engine.core :refer :all]))

(meditations
  "Lists can be expressed by function or a quoted form"
  (= '(1 2 3 4 5) (list 1 2 3 4 5))

  "They are Clojure seqs (sequences), so they allow access to the first"
  (= 1 (first '(1 2 3 4 5)))

  "As well as the rest"
  (= '(2 3 4 5) (rest '(1 2 3 4 5)))

  "Count your blessings"
  (= 3 (count '(dracula dooku chocula)))

  "Before they are gone"
  (= 0 (count '()))

  "The rest, when nothing is left, is empty"
  (= '() (rest '(100)))

  "Construction by adding an element to the front is easy"
  (= '(:a :b :c :d :e) (cons :a '(:b :c :d :e)))
  ;; Returns a new seq where x is the first element and seq is the rest.
  ;; prepend 1 to a list
  ; (cons 1 '(2 3 4 5 6))
  ;;=> (1 2 3 4 5 6)

  ;; notice that the first item is not expanded
  ; (cons [1 2] [4 5 6])
  ;;=> ([1 2] 4 5 6)

  "Conjoining an element to a list isn't hard either"
  (= '(:e :a :b :c :d) (conj '(:a :b :c :d) :e))
  ;; notice that conjoining to a vector is done at the end
  ; (conj [1 2 3] 4)
  ;;=> [1 2 3 4]

  ;; notice conjoining to a list is done at the beginning
  ; (conj '(1 2 3) 4)
  ;;=> (4 1 2 3)

  ; (conj ["a" "b" "c"] "d")
  ;;=> ["a" "b" "c" "d"]

  ;; conjoining multiple items is done in order
  ; (conj [1 2] 3 4)
  ;;=> [1 2 3 4]

  ; (conj '(1 2) 3 4)
  ;;=> (4 3 1 2)

  ; (conj [[1 2] [3 4]] [5 6])
  ;;=> [[1 2] [3 4] [5 6]]

  ;; conjoining to maps only take items as vectors of length exactly 2
  ; (conj {1 2, 3 4} [5 6])
  ;;=> {5 6, 1 2, 3 4}

  ; (conj {:firstname "John" :lastname "Doe"} {:age 25 :nationality "Chinese"})
  ;;=> {:nationality "Chinese", :age 25, :firstname "John", :lastname "Doe"}

  ;; conj on a set
  ; (conj #{1 3 4} 2)
  ;;=> #{1 2 3 4}

  ;; conjoin nil with x or xs
  ; (conj nil 3)
  ;;=> (3)

  ; (conj nil 3 4)
  ;;=> (4 3)

  "You can use a list like a stack to get the first element"
  (= :a (peek '(:a :b :c :d :e)))

  "Or the others"
  (= '(:b :c :d :e) (pop '(:a :b :c :d :e)))

  "But watch out if you try to pop nothing"
  (= "No dice!" (try
          (pop '())
          (catch IllegalStateException e
            "No dice!")))

  "The rest of nothing isn't so strict"
  (= '() (try
          (rest '())
          (catch IllegalStateException e
            "No dice!"))))
