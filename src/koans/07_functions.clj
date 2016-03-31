(ns koans.07-functions
  (:require [koan-engine.core :refer :all]))

(defn multiply-by-ten [n]
  (* 10 n))

(defn square [n] (* n n))

(meditations
  "Calling a function is like giving it a hug with parentheses"
  (= 81 (square 9))

  "Functions are usually defined before they are used"
  (= 20 (multiply-by-ten 2))

  "But they can also be defined inline"
  (= 10 ((fn [n] (* 5 n)) 2))

  "Or using an even shorter syntax"
  (= 60 (#(* 15 %) 4))
  ; These are anonymous functions. The hash and parenthesizes will denote an anonymous function.
  ; Anywhere you see a percentages sign, they are the passed in values.

  "Even anonymous functions may take multiple arguments"
  (= 15 (#(+ %1 %2 %3) 4 5 6))

  "Arguments can also be skipped"
  (= 30 (#(* 15 %2) 1 2))

  "One function can beget another"
  (= 9 (((fn [] #(+ % %2))) 4 5))
  ;    ^^^定义一个返回值为匿名函数的匿名函数
  ;    ||执行外层的匿名函数，返回#()定义的匿名函数
  ;    |执行返回的那个匿名函数 并传递4 5两个参数
  ; magic

  "Functions can also take other functions as input"
  (= 20 ((fn [f] (f 4 5))
           #(* %1 %2)))

  "666"
  (= 20 ((fn [f] (f 4 5)) *))

  "Higher-order functions take function arguments"
  (= 25 (___
          (fn [n] (* n n))))

  "But they are often better written using the names of functions"
  (= 25 (___ square)))
