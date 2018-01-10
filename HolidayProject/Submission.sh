mkdir $HOME/asnlib >& temp.txt

mkdir $HOME/maps >& temp.txt

cp $ASNLIB/mapA.txt $HOME/maps >& temp.txt

cp -a $ASNLIB/*.java $HOME >& temp.txt

cp -a $ASNLIB/input1.txt $HOME/asnlib >& temp.txt

cp $ASNLIB/DeliveryMap.class $HOME >& temp.txt

cp $ASNLIB/Project.class $HOME >& temp.txt

cp $ASNLIB/Parser.class $HOME >& temp.txt

cp $ASNLIB/TestCase.class $HOME >& temp.txt

java -classpath "$LIB/javaparser-core-3.2.7.jar:." Project

cat $HOME/gradefile.txt > $vocareumGradeFile

ls asnlib

rm -r $HOME/asnlib >& temp.txt

rm -r $HOME/maps >& temp.txt

rm -r $HOME/temp* >& temp.txt

rm *.class >& temp.txt

rm *.txt >& temp.txt

echo " " > temp.txt

rm temp.txt
