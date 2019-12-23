# Template for pre-commit hook.
# Install: cp pre-commit.tpl .git/hooks/pre-commit && chmod +x .git/hooks/pre-commit

echo "####################################################################"
echo "##### Running ./mvnw fmt:check to check for Google Java Format #####"
echo "####################################################################"
./mvnw fmt:check


echo "####################################################################"
echo "##### Running ./mvnw test to run test-case #####"
echo "####################################################################"
./mvnw test

#TODO nicer output