
; ----Part 1: Calling basic functions
(+ 1 1)
(* 3 2)
; (println "Give me some Clojure!")
(- 8 1 2)
(< 1 2 3)
(str "hello ," "World !")
(= 1 1.0)
(class true)
; (if false (println "true") (println "false"))


; ----Part 2: Lists, Maps, Sets, and Vectors

; Vectors
[:hutt :wookie :ewok]

(first [:hutt :wookie :ewok])
(rest [:hutt :wookie :ewok])

; Maps
{:chewie :wookie :lea :human}
(def mentors {:darth-vader "obi wan", :luke "yoda"})
(mentors :luke)
(merge-with + {:y-wing 2, :x-wing 4} {:tie-fighter 2 :x-wing 3})

; ----Part 3: Defining Functions
(defn force-it
  "The first function a young Jedi needs"
  [name]
  (str "Use the force," name))
; (println (force-it "liuzhuo"))

; ----Part 4: Binding and destructuring
(def line [[0 0] [10 20]])
(defn line-end [ln] (last line))
(defn line-end [[_ second]] second)
; (println (line-end line))

(def person {:name "Jabba" :profession "Gangster"})
(let [{name :name} person] (str "The person's name is " name))

; ----Part 5: Anonymous Functions
(def people ["Lea ", "Han Solo"])
(defn twice-count [w] (* 2 (count w)))
(twice-count "Lando")
(map twice-count people)

(map (fn [w] (* 2 (count w))) people)
(map #(* 2 (count %)) people)

; (println (reduce str people))
; (println (reduce + [1 2 3 4 5]))

; ----Part 6: Recursion 
(defn size [v]
  (if (empty? v)
    0
    (inc (size (rest v)))))

(size [1 2 3])


; ----Part 7: Sequences
(every? number? [1 2 3 :four])

(def words ["luke" "chewie" "han" "lando"])
(filter (fn [word] (> (count word) 4)) words)

(def colors ["red" "blue"])
(def toys ["block" "car"])
(defn small-word? [w] (< (count w) 4))

(for [x colors, y toys, :when (small-word? y)]
  (str "I like " x " " y "s")) 

; ----Part 8: Lazy Evaluation
(range 1 10)
(take 5 (cycle [:lather :rinse :repeat]))
(take 5 (drop 2 (cycle [:lather :rinse :repeat])))
(->> [:lather :rinse :repeat] (cycle) (drop 2) (take 5))

(defn fib-pair [[a b]] [b (+ a b)])
(take 5 (map first (iterate fib-pair [1 1])))

(defn factorial [n] (apply * (take n (iterate inc 1))))
(factorial 5)

; ----Part 9: Macros 

(defmacro unless [test body]
  (list 'if (list 'not test) body))

; ----Part 10: Concurrence Programming
; Ref (STM)
; Atoms
; Agents

(def danger (atom "Split at your own risk."))
; (println danger)
; (println @danger)

