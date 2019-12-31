# Template for pre-commit hook.
# Install: cp pre-commit.tpl .git/hooks/pre-commit && chmod +x .git/hooks/pre-commit

echo "####################################################################"
echo "##### Running ./mvnw clean verify #####"
echo "####################################################################"
./mvnw clean verify || exit 1

#TODO nicer output
