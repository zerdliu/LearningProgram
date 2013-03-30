;  They are gathered into a list by adding an ampersand and a name for the list at the end of the parameter list. 
;  exponents 是一个list ， 还需要再理解reduce的用法
(defn power [base & exponents]
  ; Using java.lang.Math static method pow.
  (reduce #(Math/pow %1 %2) base exponents))
(println (power 2 3 2 2)) ; 2 to the 3rd = 8; 8 to the 4th = 4096


(defn parting
  "returns a String parting in a given language"
  ; 参数的多种选择, 最基本的函数参数是[name language]
  ([] (parting "World"))
  ([name] (parting name "en"))
  ([name language]
    ; condp is similar to a case statement in other languages.
    ; It is described in more detail later.
    ; It is used here to take different actions based on whether the
    ; parameter "language" is set to "en", "es" or something else.
    ; condp 继续对参数进行选择处理
    (condp = language
      "en" (str "Goodbye, " name)
      "es" (str "Adios, " name)
      (throw (IllegalArgumentException.
        (str "unsupported language " language))))))

(println (parting)) ; -> Goodbye, World
(println (parting "Mark")) ; -> Goodbye, Mark
(println (parting "Mark" "es")) ; -> Adios, Mark
; (println (parting "Mark", "xy"))
; -> java.lang.IllegalArgumentException: unsupported language xy



(def years [1940 1944 1961 1985 1987 2000])
; 注意两种匿名函数的互换
(filter (fn [year] (even? year)) years) ; long way w/ named arguments -> (1940 1944)
(println (filter #(even? %) years)) ; short way where % refers to the argument


; test-fn其实是一个匿名函数,这里貌似只起到了一个占位的作用，强大
(defn pair-test [test-fn n1 n2]
  (if (test-fn n1 n2) "pass" "fail"))

; Use a test-fn that determines whether
; the sum of its two arguments is an even number.
(println (pair-test #(even? (+ %1 %2)) 3 5)) ; -> pass

;         method name , dispatch function
(defmulti what-am-i class) ; class is the dispatch function
;         method name , dispatch value , parameter list
(defmethod what-am-i Number [arg] (println arg "is a Number"))
(defmethod what-am-i String [arg] (println arg "is a String"))
(defmethod what-am-i :default [arg] (println arg "is something else"))
(what-am-i 19) ; -> 19 is a Number
(what-am-i "Hello") ; -> Hello is a String
(what-am-i true) ; -> true is something else


(defn callback1 [n1 n2 n3] (+ n1 n2 n3)) ; uses all three arguments
(defn callback2 [n1 _ n3] (+ n1 n3)) ; only uses 1st & 3rd arguments
(defn caller [callback value]
  (callback (+ value 1) (+ value 2) (+ value 3)))
; 我使用了-> 挺好用的
(-> (caller callback1 10) println) ; 11 + 12 + 13 -> 36
(-> (caller callback2 10) println) ; 11 + 13 -> 24



(defn teenager? [age] (and (>= age 13) (< age 20)))
; complement 取反？？
; complement returns a new function
(def non-teen? (complement teenager?))
(println (non-teen? 47)) ; -> true



(defn times2 [n] (* n 2))
(defn minus3 [n] (- n 3))
; Note the use of def instead of defn because comp returns
; a function that is then bound to "my-composition".
; 从又向左执行
(def my-composition (comp times2 minus3))
; (def my-composition (comp minus3 times2))
(println (my-composition 4)) ; 4*2 - 3 -> 5


; it provides fixed values for initial parameters and calls the original function
; 仍然会调用以前的函数，感觉像一个宏代换
; Note the use of def instead of defn because partial returns
; a function that is then bound to "times2".
(def times2 (partial * 2))
(times2 3 4) ; 2 * 3 * 4 -> 24



(defn- polynomial
  "computes the value of a polynomial
   with the given coefficients for a given value x"
  [coefs x]
  ; For example, if coefs contains 3 values then exponents is (2 1 0).
  (let [exponents (reverse (range (count coefs)))]
    ; Multiply each coefficient by x raised to the corresponding exponent
    ; and sum those results.
    ; coefs go into %1 and exponents go into %2.
    (apply + (map #(* %1 (Math/pow x %2)) coefs exponents))))

(defn- derivative
  "computes the value of the derivative of a polynomial
   with the given coefficients for a given value x"
  [coefs x]
  ; The coefficients of the derivative function are obtained by
  ; multiplying all but the last coefficient by its corresponding exponent.
  ; The extra exponent will be ignored.
  (let [exponents (reverse (range (count coefs)))
        derivative-coefs (map #(* %1 %2) (butlast coefs) exponents)]
    (polynomial derivative-coefs x)))

(def f (partial polynomial [2 1 3])) ; 2x^2 + x + 3
(def f-prime (partial derivative [2 1 3])) ; 4x + 1

(println "f(2) =" (f 2)) ; -> 13.0
(println "f'(2) =" (f-prime 2)) ; -> 9.0"")"")



