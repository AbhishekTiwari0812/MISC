print "Hello world!\n";
print 'Hello world\n';
@My_Array=("First","Second","Third");
$My_Array=@My_Array;
print "\n$My_Array";
%My_HashTable=('key','value','Anish',19,'Abhishek',20);
#we can also assign values in hash table by
$My_HashTable{"NewEntry"}="New_value";
$martin = v77.97.114.116.105.110; #assigning ASCII values
print "\n$martin";
@New_Array=qw/this is a new method to create an array/;
print "\n@New_Array[1]";
@Alphabets=(a..z);
print "\n@Alphabets";
push @Alphabets,"New Entry";
print "\n@Alphabets";
pop @Alphabets;
print "\n@Alphabets";
unshift @Alphabets,"New Entry";
print "\n@Alphabets";
shift @Alphabets;
print "\n@Alphabets";
#Splicing the array
#splice @ARRAY, OFFSET [ , LENGTH [ , LIST ] ];
splice @Alphabets,0,13,(A..M);
print "\n@Alphabets";
#spliting String
#split(delimiter,StringVar);
#Array to String
#join (Connector,ArrayName);
#Sorting array
sort (@My_Array);
print "\n".localtime();
print "\n".gmtime();
$bar="This is foo and again foo";
if($bar=~ /foo/)
	{
		print "\nBar has a substring foo"
	}
else
{
	print "\nit doesn't have substring foo";
}
$time=localtime();
my ($hours, $minutes, $seconds) = ($time =~ m/(\d+):(\d+):(\d+)/);
print "\n$hours:$minutes:$seconds";
$string = "The cat sat on the mat";
$string =~ s/at/dog/;
print "\n$string";












