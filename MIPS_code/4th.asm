#if (i == j) f = g + h; else f = g – h;
#beq branch if equal
#bne branch if not equal
.data
i: .word 10
j: .word 20
g: .word 30
f: .word 0
h: .word 16
.text
main:
lw $t0,i
lw $t1,j
lw $s0,g
lw $s1,h
lw $s2,f
bne $t0,$t1,else	#jumps tp else label
add $s2,$s0,$s1
else:
sub $s2,$s0,$s1
la $v0,f
sw $s0,($v0)#store the data at the address of f
