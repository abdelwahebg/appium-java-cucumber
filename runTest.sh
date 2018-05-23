#!/usr/bin/env bash

# Created by Aldo Christian on 23/05/18.

EMULATION_OPTION=(1 2 3 4 5 6 7 8 9 10 11)
EMULATOR_API_OPTION=(1 2 3 4 5 6)
PLATFORM_OPTION=(1 2 3 4)
BROWSER_OPTION=(1 2 3 4)
TEST_TYPE_OPTION=(1 2 3)
YES_NO_OPTION=(1 2)

# Copy Default File for Cucumber Automation
rm -rf target/
cp -f temp/CUCUMBER.xml src/testsuites/
cp -f temp/CucumberTestRunner.java src/test/java/bukalapak/cucumber/

echo ""
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
    echo "Sorry, please choose between 1 ~ 4 :"
    read option
done

if [[ " ${PLATFORM_OPTION[*]} " == *" ${option} "* ]]; then

    case $option in
        1)
            platform="android"
            module="APP"
            ;;
        2)
            platform="web"
            module="WEB"
            ;;
        3)
            platform="pwa"
            module="PWA"
            ;;
        4)
            echo ""
            echo "Bye, Thankyou!"
            exit 0
            ;;
        *)
            echo ""
            echo "Platform not found"
            exit 1
            ;;
    esac

    # Change module
    sed -i '' s/TEMPORARY_MODULE/${module}/g src/testsuites/CUCUMBER.xml

    if [[ $option = *"1"* ]]; then

        echo ""
        echo "=================================="
        echo "|   Choose Emulator ?            |"
        echo "|   1. Yes                       |"
        echo "|   2. No ( Use default )        |"
        echo "=================================="
        echo ""
        echo "Please choose between 1 ~ 2 :"
        read emulator_option

        while [[ " ${YES_NO_OPTION[*]} " != *" ${emulator_option} "* ]];
        do
            echo "Sorry, please choose between 1 ~ 2 :"
            read emulator_option
        done

        if [[ $emulator_option = *"1"* ]]; then

            echo ""
            echo "=================================="
            echo "|   Please choose emulator type: |"
            echo "|   1. API 19 (4.4) KitKat       |"
            echo "|   2. API 22 (5.1.1) Lollipop   |"
            echo "|   3. API 23 (6.0) Marshmallow  |"
            echo "|   4. API 25 (7.1.1) Nougat     |"
            echo "|   5. API 26 (8.0) Oreo         |"
            echo "|   6. Custom Device             |"
            echo "=================================="
            echo ""
            echo "Please choose between 1 ~ 6 :"
            read api_option

            while [[ " ${EMULATOR_API_OPTION[*]} " != *" ${api_option} "* ]];
            do
                echo "Sorry, please choose between 1 ~ 6 :"
                read api_option
            done

            case $api_option in
                1)
                    api="API19"
                    version="4.4"
                    ;;
                2)
                    api="API22"
                    version="5.1.1"
                    ;;
                3)
                    api="API23"
                    version="6.0"
                    ;;
                4)
                    api="API25"
                    version="7.1.1"
                    ;;
                5)
                    api="API26"
                    version="8.0"
                    ;;
                6)
                    echo ""
                    echo "Please input device id / name :"
                    read api
                    echo ""
                    echo "Please input device version :"
                    read version
                    ;;
                *)
                    echo ""
                    echo "API not found"
                    exit 1
                    ;;
            esac

            # Change Desired Device
            sed -i '' s/TEMPORARY_DEVICE/${api}/g src/testsuites/CUCUMBER.xml
            sed -i '' s/TEMPORARY_PLATFORM/${version}/g src/testsuites/CUCUMBER.xml

            if [[ $emulator_option = *"6"* ]]; then
                echo ""
                echo "Automation will be running on Device with id : ${api} and (${version}) android"
            else
                echo ""
                echo "Emulation will be running on Emulator with ${api} (${version})"
            fi

        elif [[ $emulator_option = *"2"* ]]; then

            # Use Default API 22 Emulator
            sed -i '' s/TEMPORARY_DEVICE/API22/g src/testsuites/CUCUMBER.xml
            sed -i '' s/TEMPORARY_PLATFORM/5.1.1/g src/testsuites/CUCUMBER.xml
            echo ""
            echo "Emulation will be running on Emulator with API 22 (5.1.1)"
        fi
    else

        echo ""
        echo "=================================="
        echo "|   Choose Browser               |"
        echo "|   1. Firefox                   |"
        echo "|   2. Google Chrome             |"
        echo "|   3. Safari                    |"
        echo "|   4. Opera (Don't use for Web) |"
        echo "=================================="
        echo ""
        echo "Please choose between 1 ~ 4 :"
        read browser_type

        while [[ " ${BROWSER_OPTION[*]} " != *" ${browser_type} "* ]];
        do
            echo "Sorry, Please choose between 1 ~ 4 :"
            read browser_type
        done

        case $browser_type in
            1)
                browser="FIREFOX"
                ;;
            2)
                browser="CHROME"
                ;;
            3)
                browser="SAFARI"
                ;;
            4)
                browser="OPERA"
                ;;
            *)
                echo ""
                echo "Browser not found"
                exit 1
                ;;
        esac

        if [[ $option = *"3"* ]]; then

            echo ""
            echo "========================================="
            echo "|   Please Choose Screen Emulation      |"
            echo "|   1. Nexus 5                          |"
            echo "|   2. Nexus 5X                         |"
            echo "|   3. Nexus 7                          |"
            echo "|   4. Pixel 2                          |"
            echo "|   5. Pixel 2 XL                       |"
            echo "|   6. Iphone 5/SE                      |"
            echo "|   7. Iphone 6/7/8                     |"
            echo "|   8. Iphone 6/7/8 Plus                |"
            echo "|   9. Iphone X                         |"
            echo "|   10. Ipad                            |"
            echo "|   11. Ipad Pro                        |"
            echo "========================================="
            echo ""
            echo "Please choose between 1 ~ 11 :"
            read emulation_type

            while [[ " ${EMULATION_OPTION[*]} " != *" ${emulation_type} "* ]];
            do
                echo "Sorry, Please choose between 1 ~ 11 :"
                read emulation_type
            done

            case $emulation_type in
                1)
                    emulation="Nexus\ 5"
                    emulation_string="Nexus 5"
                    ;;
                2)
                    emulation="Nexus\ 5X"
                    emulation_string="Nexus 5X"
                    ;;
                3)
                    emulation="Nexus\ 7"
                    emulation_string="Nexus 7"
                    ;;
                4)
                    emulation="Pixel\ 2"
                    emulation_string="Pixel 2"
                    ;;
                5)
                    emulation="Pixel\ 2\ XL"
                    emulation_string="Pixel 2 XL"
                    ;;
                6)
                    emulation="Iphone\ 5\\/SE"
                    emulation_string="Iphone 5/SE"
                    ;;
                7)
                    emulation="Iphone\ 6\\/7\\/8"
                    emulation_string="Iphone 6/7/8"
                    ;;
                8)
                    emulation="Iphone\ 6\\/7\\/8\ Plus"
                    emulation_string="Iphone 6/7/8 Plus"
                    ;;
                9)
                    emulation="Iphone\ X"
                    emulation_string="Iphone X"
                    ;;
                10)
                    emulation="Ipad"
                    emulation_string="Ipad"
                    ;;
                11)
                    emulation="Ipad\ Pro"
                    emulation_string="Ipad Pro"
                    ;;
                *)
                    echo ""
                    echo "Emulation not found"
                    exit 1
                    ;;
            esac
        fi

        # Change Browser Capabilities
        sed -i '' s/TEMPORARY_BROWSER/${browser}/g src/testsuites/CUCUMBER.xml
        # Emulate Browser Screen
        sed -i '' s/TEMPORARY_EMULATION/"${emulation}"/g src/testsuites/CUCUMBER.xml

        if [[ $option = *"2"* ]]; then
            echo ""
            echo "Automation will be running on ${browser} browser"
        else


            echo ""
            echo "Automation will be running on ${browser} browser with ${emulation_string} screen"
        fi
    fi

    echo ""
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
        echo "Sorry, Please choose between 1 ~ 3 :"
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
            sed -i '' s/TEMPORARY_TAGS/${tag}/g src/test/java/bukalapak/cucumber/CucumberTestRunner.java
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

# Open Cucumber Report on Browser
open target/cucumber-html-reports/overview-features.html

