(ns koans.18-macros
  (:require [koan-engine.core :refer :all]))

; http://www.isnowfy.com/clojure-macro/

; defmacro的作用就是在代码编译的时候，会把defmacro当作是函数运行一次，并且把这个的返回结果替换到原有的位置上去
; `表示syntax quote，'表示quote，~表示unquote，~@表示unquote splicing。
; 如果某段代码前面加了'就表示这段代码被quote而不会去求值了，而`的syntax quote则表示会把相应的变量变成有namespace的形式
; ~和`是搭配使用的，~必须在`的后面，并且~的数量不能超过`的数量，~是用来将变量的值替换到相应位置
; 而~@的作用和~类似，不过~@不但会替换掉值并且会把值打撒。

(defmacro hello [x]
  (str "Hello, " x))

(defmacro infix [form]
  (list (second form) (first form) (nth form 2)))

(defmacro infix-better [form]
  `(~(second form) ; Note the syntax-quote (`) and unquote (~) characters!
    __
    __ ))

(defmacro r-infix [form]
  (cond (not (seq? form))
        __
        (= 1 (count form))
        `(r-infix ~(first form))
        :else
        (let [operator (second form)
              first-arg (first form)
              others __]
          `(~operator
            (r-infix ~first-arg)
            (r-infix ~others)))))

(meditations
  "Macros are like functions created at compile time"
  (= "Hello, Macros!" (hello "Macros!"))

  "I can haz infix?"
  (= 10 (infix (9 + 1)))

  "Remember, these are nothing but code transformations"
  (= '(+ 9 1) (macroexpand '(infix (9 + 1))))

  "You can do better than that - hand crafting FTW!"
  (= __ (macroexpand '(infix-better (10 * 2))))

  "Things don't always work as you would like them to... "
  (= __ (macroexpand '(infix-better ( 10 + (2 * 3)))))

  "Really, you don't understand recursion until you understand recursion"
  (= 36 (r-infix (10 + (2 * 3) + (4 * 5)))))
