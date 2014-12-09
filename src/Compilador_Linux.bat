#
# UOC - TDP - 2014
# Compilador classes java de la PAC4
# Swing Team
#
echo
echo
echo "*********************************************************"
echo "*********************************************************"
echo "******                                             ******"
echo "******   P R O C E S   D E   C O M P I L A C I O   ******"
echo "******                                             ******"
echo "*********************************************************"
echo "*********************************************************"
echo
echo "Proces de compilacio de les classes java del projecte"
echo "corresponent a la PAC4 de l'assignatura" 
echo "Tecniques de Desenvolupament del Programari de la UOC."
echo
echo "Projecte realizat per:"
echo
echo "Swing Team - 2014"
echo "================="
echo
echo
read -n1 -r -p "Premi una tecla per a iniciar el proces"
echo
echo "*** Proces iniciat ***"
echo
echo "Compilant les classes de la carpeta 'uoc/tdp/pac4/st/common'"
javac -classpath . uoc/tdp/pac4/st/common/*.java

echo "Compilant les classes de la carpeta 'uoc/tdp/pac4/st/common/dto'"
javac -classpath . uoc/tdp/pac4/st/common/dto/*.java

echo "Compilant les classes de la carpeta 'uoc/tdp/pac4/st/common/managers'"
javac -classpath . uoc/tdp/pac4/st/common/managers/*.java

echo "Compilant les classes de la carpeta 'uoc/tdp/pac4/st/common/ui'"
javac -classpath . uoc/tdp/pac4/st/common/ui/*.java

echo "Compilant les classes de la carpeta 'uoc/tdp/pac4/st/client.cf'"
javac -classpath . uoc/tdp/pac4/st/client/cf/*.java

echo "Compilant les classes de la carpeta 'uoc/tdp/pac4/st/client.e'"
javac -classpath . uoc/tdp/pac4/st/client/e/*.java

echo "Compilant les classes de la carpeta 'uoc/tdp/pac4/st/client.m'"
javac -classpath . uoc/tdp/pac4/st/client/m/*.java

echo "Compilant les classes de la carpeta 'uoc/tdp/pac4/st/client.cx'"
javac -classpath . uoc/tdp/pac4/st/client/cx/*.java

echo "Compilant les classes de la carpeta 'uoc/tdp/pac4/st/rmi'"
javac -classpath . uoc/tdp/pac4/st/rmi/*.java

echo "Compilant les classes de la carpeta 'uoc/tdp/pac4/st/server'"
javac -classpath . uoc/tdp/pac4/st/server/*.java

echo "Compilant les classes de la carpeta 'uoc/tdp/pac4/st'"
javac -classpath . uoc/tdp/pac4/st/*.java

echo "Compilant la implementacio de la interface RMI"
rmic -classpath . uoc.tdp.pac4.st.rmi.ETallerStocksImpl
echo "*** Proces finalitzat ***"
echo
read -n1 -r -p "Premi una tecla per a sortir"
echo


