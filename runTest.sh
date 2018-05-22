#!/usr/bin/env bash
PLATFORM_OPTION=(1 2 3 4)
TEST_TYPE_OPTION=(1 2 3)

# Copy Default File for Cucumber Automation
cp -f temp/CUCUMBER.xml src/testsuites/
cp -f temp/CucumberTestRunner.java src/test/java/bukalapak/cucumber/

echo "=================================="
echo "|   Please choose platform:      |"
echo "|   1. Android                   |"
echo "|   2. Desktop                   |"
echo "|   3. Pwa                       |"
echo "|   4. Exit                      |"
echo "=================================="
echo ""
echo "Please choose between 1 ~ 4 :"
read option

while [[ " ${PLATFORM_OPTION[*]} " != *" ${option} "* ]];
do
    echo "Please choose between 1 ~ 4 :"
    read option
done

if [[ " ${PLATFORM_OPTION[*]} " == *" ${option} "* ]]; then

    if [[ $option = *"4"* ]]; then
        echo ""
        echo "Bye, Thankyou!"
        exit 0
    fi

    case $option in
        [1]*)
            platform="android"
            module="APP"
            ;;
        [2]*)
            platform="web"
            module="WEB"
            ;;
        [3]*)
            platform="pwa"
            module="PWA"
            ;;
        *)
            platform="Platform not found"
            ;;
    esac

    # Change module
    sed -i '' s/TEMPORARY_MODULE/${module}/g src/testsuites/CUCUMBER.xml

    echo "=================================="
    echo "|   Please choose test type:     |"
    echo "|   1. By Feature                |"
    echo "|   2. By Tag                    |"
    echo "|   3. Exit                      |"
    echo "=================================="
    echo ""
    echo "Please choose between 1 ~ 3 :"
    read test_type

    while [[ " ${TEST_TYPE_OPTION[*]} " != *" ${test_type} "* ]];
    do
        echo "Please choose between 1 ~ 3 :"
        read test_type
    done

    if [[ " ${TEST_TYPE_OPTION[*]} " == *" ${test_type} "* ]]; then

        if [[ $test_type = *"1"* ]]; then

            echo ""
            echo "What feature do you want to run ?"
            read feature
            echo ""
            echo "Running ${feature} on ${platform} platform"
            echo "Please wait ..."

            # Change Desired Test
            sed -i '' s/TEMPORARY_TAGS//g src/test/java/bukalapak/cucumber/CucumberTestRunner.java
            sed -i '' s/TEMPORARY_FEATURES/src\\/test\\/resources\\/features\\/${platform}\\/${feature}/g src/test/java/bukalapak/cucumber/CucumberTestRunner.java

            # Run Test
            gradle clean test

        elif [[ $test_type = *"2"* ]]; then

            echo ""
            echo "Please define tag to run?"
            read tag

            echo ""
            echo "Running automation by ${tag} tag on ${platform} platform"
            echo "Please wait ..."

            # Change Desired Test
            sed -i '' s/TEMPORARY_TAGS/@${tag}/g src/test/java/bukalapak/cucumber/CucumberTestRunner.java
            sed -i '' s/TEMPORARY_FEATURES/src\\/test\\/resources\\/features/g src/test/java/bukalapak/cucumber/CucumberTestRunner.java

            # Run Test
            gradle clean test

        elif [[ $test_type = *"3"* ]]; then

            echo ""
            echo "Bye, Thankyou!"
            exit 0

        fi
    fi
fi

