

(println (. java.util.Calendar APRIL)) ; -> 3
(println (java.util.Calendar/APRIL)) ; -> 3
; (. Calendar APRIL) ; works if the Calendar class was imported
; java.util.Calendar/APRIL

(import
  '(java.util Calendar))
(println Calendar/APRIL) ; works if the Calendar class was imported


(println (. Math pow 2 4)) ; -> 16.0
(println (Math/pow 2 4))
