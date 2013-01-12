#!/usr/bin/env io

Vehicle := Object clone
Vehicle description := "Something to take you places"
Vehicle description = "Something to take you far away"

#Vehicle nonexistingSlot = "This won't work"
Vehicle slotNames println
Vehicle type println
Object type println

Car := Vehicle clone
Car slotNames println
Car type println
Car description println

ferrari := Car clone
ferrari slotNames println
ferrari type println


