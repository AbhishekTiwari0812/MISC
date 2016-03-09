.data 
x: .word 5
.text
j main
sum:
li $t3,2
li $t4,3
li $t5,5
mul $t3,$t3,$t4
add $t3,$t3,$t5
jr $ra
main:
li $s0,1
li $t1,1
lw $t0,x
jal sum
move $a0,$t3
li $v0,1
syscall
