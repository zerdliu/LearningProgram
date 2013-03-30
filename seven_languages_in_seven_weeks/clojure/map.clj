(def person {
  :name "Mark Volkmann"
  :address {
    :street "644 Glen Summit"
    :city "St. Charles"
    :state "Missouri"
    :zip 63304}
  :employer {
    :name "Object Computing, Inc."
    :address {
      :street "12140 Woodcrest Executive Drive, Suite 250"
      :city "Creve Coeur"
      :state "Missouri"
      :zip 63141}}})

(println (get-in person [:employer :address :city]))
(println (-> person :employer :address :city)) ; explained below
(println (reduce get person [:employer :address :city])) ; explained below

; assoc-in 并没有改变值
(println (-> (assoc-in person [:employer :address :city] "Clayton") :employer :address :city))
(println (-> person :employer :address :city)) ; explained below

