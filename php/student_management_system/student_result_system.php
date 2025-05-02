<?php
// Student Result Management System
$filename = "students.txt";

function showMenu() {
    echo "\n--- Student Result Management System ---\n";
    echo "1. Add Student\n";
    echo "2. Enter Marks\n";
    echo "3. View All Results\n";
    echo "4. Search by Student ID\n";
    echo "5. Delete Student\n";
    echo "6. Update Marks\n";
    echo "7. Exit\n";
    echo "Choose an option: ";
}


function addStudent($filename) {
    echo "Enter Student ID: ";
    $id = trim(fgets(STDIN));

    if (file_exists($filename)) {
        $lines = file($filename);
        foreach ($lines as $line) {
            $parts = explode('|', trim($line));
            if ($parts[0] == $id) {
                echo "Error: Student ID '$id' already exists.\n";
                return;
            }
        }
    }

    echo "Enter Student Name: ";
    $name = trim(fgets(STDIN));
    $record = "$id|$name|0|0|0|0\n";
    file_put_contents($filename, $record, FILE_APPEND);
    echo "Student added successfully!\n";
}

function enterMarks($filename) {
    echo "Enter Student ID: ";
    $id = trim(fgets(STDIN));
    $lines = file($filename);
    $found = false;

    foreach ($lines as $index => $line) {
        $parts = explode('|', trim($line));
        if ($parts[0] == $id) {
            if ($parts[2] != 0 || $parts[3] != 0 || $parts[4] != 0) {
                echo "Marks already entered for this student. Use 'Update Marks' option.\n";
                return;
            }
            echo "Enter marks for Subject 1: ";
            $s1 = trim(fgets(STDIN));
            echo "Enter marks for Subject 2: ";
            $s2 = trim(fgets(STDIN));
            echo "Enter marks for Subject 3: ";
            $s3 = trim(fgets(STDIN));
            $total = $s1 + $s2 + $s3;
            $lines[$index] = "$id|{$parts[1]}|$s1|$s2|$s3|$total\n";
            $found = true;
            break;
        }
    }

    if ($found) {
        file_put_contents($filename, implode('', $lines));
        echo "Marks entered successfully!\n";
    } else {
        echo "Student ID not found.\n";
    }
}


function viewResults($filename) {
    if (!file_exists($filename) || filesize($filename) == 0) {
        echo "No student records found.\n";
        return;
    }

    $lines = file($filename, FILE_IGNORE_NEW_LINES);
    echo "ID\tName\tS1\tS2\tS3\tTotal\tPercentage\tGrade\n";

    foreach ($lines as $line) {
        $parts = explode('|', $line);
        if (count($parts) < 6) continue;

        list($id, $name, $s1, $s2, $s3, $total) = $parts;
        $percentage = ($total != 0) ? $total / 3 : 0;
        $grade = ($total != 0) ? getGrade($percentage) : "-";

        // Only show marks if they are entered, otherwise show "-"
        $s1 = ($s1 != 0) ? $s1 : "-";
        $s2 = ($s2 != 0) ? $s2 : "-";
        $s3 = ($s3 != 0) ? $s3 : "-";
        $total = ($total != 0) ? $total : "-";
        $percentage = ($total != 0) ? round($percentage, 2) . "%" : "-";
        
        echo "$id\t$name\t$s1\t$s2\t$s3\t$total\t$percentage\t\t$grade\n";
    }
}

function getGrade($percentage) {
    if ($percentage >= 90) return 'A';
    if ($percentage >= 75) return 'B';
    if ($percentage >= 50) return 'C';
    if ($percentage >= 35) return 'D';
    return 'F';
}

function searchStudent($filename) {
    echo "Enter Student ID to search: ";
    $id = trim(fgets(STDIN));
    $lines = file($filename);
    foreach ($lines as $line) {
        $parts = explode('|', trim($line));
        if ($parts[0] == $id) {
            $percentage = ($parts[5] != 0) ? $parts[5] / 3 : 0;
            $grade = ($percentage != 0) ? getGrade($percentage) : "-";
            echo "\nID: $parts[0]\nName: $parts[1]\nSubject 1: $parts[2]\nSubject 2: $parts[3]\nSubject 3: $parts[4]\nTotal: $parts[5]\nPercentage: " . round($percentage, 2) . "%\nGrade: $grade\n";
            return;
        }
    }
    echo "Student not found.\n";
}

function deleteStudent($filename) {
    echo "Enter Student ID to delete: ";
    $id = trim(fgets(STDIN));

    if (!file_exists($filename)) {
        echo "No student records found.\n";
        return;
    }

    $lines = file($filename, FILE_IGNORE_NEW_LINES);
    $found = false;
    $updatedLines = [];

    foreach ($lines as $line) {
        $parts = explode('|', $line);
        if ($parts[0] == $id) {
            $found = true;
            continue;
        }
        $updatedLines[] = $line;
    }

    if ($found) {
        file_put_contents($filename, implode("\n", $updatedLines) . "\n");
        echo "Student with ID $id deleted successfully.\n";
    } else {
        echo "Student with ID $id not found.\n";
    }
}




do {
    showMenu();
    $choice = trim(fgets(STDIN));

    switch ($choice) {
        case 1:
            addStudent($filename);
            break;
        case 2:
            enterMarks($filename);
            break;
        case 3:
            viewResults($filename);
            break;
        case 4:
            searchStudent($filename);
            break;
        case 5:
            deleteStudent($filename);
            break;
        case 6:
             updateMarks($filename);
             break;
    	case 7:
            echo "Exiting...\n";
            exit;
        default:
            echo "Invalid choice. Try again.\n";
    }
} while (true);
?>
