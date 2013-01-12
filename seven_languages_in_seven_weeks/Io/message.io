#!/usr/bin/env io
#
#
"Hi ho, Io" println

postOffice := Object clone
postOffice packageSender := method(call sender)
mailer := Object clone
mailer deliver := method(postOffice packageSender)
## equal
mailer deliver println
mailer println

postOffice messageTarget := method(call target)
## equal
postOffice messageTarget println
postOffice println

postOffice messageArgs := method(call message arguments)
postOffice messageName := method(call message name)
postOffice messageArgs("one",2,:three) println
postOffice messageName println
