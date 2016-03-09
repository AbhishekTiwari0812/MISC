$bar="somethingContainingFoo";
if($bar=~/foo/i)	#makes insensitive comparisons
{print "it contains foo";}
else {print "it doesn't contain foo!\n";}
$true=($bar=~/foo/);
print "\$true:=$true\n";
$time=localtime();
my ($hours,$minutes,$seconds)=($time=~m/(\d+):(\d+):(\d+)/ );
print "$time\n$hours $minutes $seconds";
