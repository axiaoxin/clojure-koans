(ns koans.02-strings
  (:require [koan-engine.core :refer :all]
            [clojure.string :as string]))

(meditations
  "A string is nothing more than text surrounded by double quotes"
  (= "hello" "hello")

  "But double quotes are just magic on top of something deeper"
  (= "world" (str 'world))

  "You can do more than create strings, you can put them together"
  (= "Cool right?" (str "Cool" " right?"))
  ; pay attention to the white space

  "You can even get certain characters"
  (= \C (get "Characters" 0))
  ; get returns a value mapped to key, not-found or nil if not present. So to get \C we have to pass in the index 0.
  ; koan-engine.runner=> (get "asd" 0)
  ; \a
  ; koan-engine.runner=> (get "asd" 1)
  ; \s
  ; koan-engine.runner=> (get "asd" -1)
  ; nil
  ; koan-engine.runner=> (get "asd" -1 \X)
  ; \X
  ; (get [1 2 3] 1)
  ; 2
  ; (get {:a 1 :b 2} :b)
  ; 2

  "Or even count the characters"
  (= 11 (count "Hello World"))

  "But strings and characters are not the same"
  (= false (= \c "c"))
  ; koan-engine.runner=> (type \q)
  ; java.lang.Character
  ; koan-engine.runner=> (type "q")
  ; java.lang.String

  "What if you only wanted to get part of a string?"
  (= "World" (subs "Hello World" 6 11))
  ; (subs s start) (subs s start end)
  ; Returns the substring of s beginning at start inclusive, and ending
  ; at end (defaults to length of string), exclusive.

  "How about joining together elements in a list?"
  (= "123" (string/join '(1 2 3)))

  "What if you wanted to separate them out?"
  (= "1, 2, 3" (string/join ", " '(1 2 3)))

  "Maybe you want to separate out all your lines"
  (= ["1" "2" "3"] (string/split-lines "1\n2\n3"))

  "You may want to make sure your words are backwards"
  (= "olleh" (string/reverse "hello"))

  "Maybe you want to find the index of the first occurence of a substring"
  (= 0 (string/index-of "hello world" "h"))

  "Or maybe the last index of the same"
  (= 13 (string/last-index-of "hello world, hello" "hello"))

  "But when something doesn't exist, nothing is found"
  (= nil (string/index-of "hello world" "bob"))

  "Sometimes you don't want whitespace cluttering the front and back"
  (= "hello world" (string/trim "  \nhello world \t \n"))

  "You can check if something is a char"
  (= true (char? \c))

  "But it may not be"
  (= false (char? "a"))

  "But chars aren't strings"
  (= false (string? \b))

  "Strings are strings"
  (= true (string? "i am str"))

  "Some strings may be blank"
  (= true (string/blank? ""))

  "Even if at first glance they aren't"
  (= true (string/blank? " \n \t  "))

  "However, most strings aren't blank"
  (= false (string/blank? "hello?\nare you out there?")))
