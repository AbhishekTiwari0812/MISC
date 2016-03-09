.data 
MyFloat1: .double 16.0
MyFloat2: .double 8.0
.text 
l.d $f2,MyFloat1
l.d $f4,MyFloat2
div.d   $f0,$f2,$f4
#move $t0,$f0	#contains quotient 
#mflo $t1	#contains remainder
