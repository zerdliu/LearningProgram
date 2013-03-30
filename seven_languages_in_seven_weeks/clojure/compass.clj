; 定义了一个protocol
(defprotocol Compass
  (direction [c])
  (left [c])
  (right [c]))

; 辅助函数
(def directions [:north :east :south :west])
(defn turn
  [base amount]
  (rem (+ base amount) (count directions)))

; 开始实现record , SimpleCompass 是Compass的一个实现
(defrecord SimpleCompass [bearing]
  Compass

  (direction [_] (directions bearing))
  (left [_] (SimpleCompass. (turn bearing 3)))
  (right [_] (SimpleCompass. (turn bearing 1)))

  Object
  (toString [this] (str "[" (direction this) "]")))


(def c (SimpleCompass. 0))
(println (left c))
(println (right c))
