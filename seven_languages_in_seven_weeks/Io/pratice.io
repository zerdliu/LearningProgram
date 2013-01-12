#!/usr/bin/env io


Object fib := method(
    n := call sender doMessage (call message argAt(0) )
    if( n == 0 or n == 1 , n, fib(n-1) + fib(n - 2) )
)

#fib(1) println
#fib(4) println
#fib(6) println


Object fib_iter := method(
    n := call sender doMessage (call message argAt(0))
    n1 := 0
    n2 := 1
    n3 := 0
    i := 0
    result := 0
    if(( n == 0 or n == 1) then ( n ) else (
        for(i , 0 , n - 2 , (
          n3 = n1 + n2
          n1 = n2
          n2 = n3
        )
        result = n3
    )))
    result
)

fib_iter(6) println
fib_iter(4) println
fib_iter(1) println
