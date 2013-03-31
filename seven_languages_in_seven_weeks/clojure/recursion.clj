

(defn factorial-1 [number]
  "computes the factorial of a positive integer
   in a way that doesn't consume stack space"
; loop special form 跟let special form 类似的地方是它们都会建立一个本地binding，但是同时它也建立一个递归点， 而这个递归点就是recur的参数里面的那个函数。loop给这些binding一个初始值。对recur 的调用使得程序的控制权返回给loop 并且给那些本地binding赋了新的值。给recur传递的参数一定要和loop所创建的binding的个数一样。同样recur只能出现在loop这个special form的最后一行。
  (loop [n number factorial 1]
    (if (zero? n)
      factorial
      (recur (dec n) (* factorial n)))))

(println (time (factorial-1 6))) ; -> "Elapsed time: 0.071 msecs"\n120"")


(defn factorial-2
  [number]
; 2 * 3 * 4 * 5  注意reduce 的使用方法
; The same result can be obtained by replacing reduce with apply, but that takes even longer.
  (reduce * (range 2 (inc number))))

(println (time (factorial-2 5))) ; -> "Elapsed time: 0.335 msecs"\n120
