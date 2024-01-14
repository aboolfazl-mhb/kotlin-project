import java.io.File
import java.io.FileNotFoundException
import java.io.PrintWriter

data class Contact(val name: String, val phoneNumber: String, val email: String, val address: String)

class PhoneBook(private val databaseFileName: String) {
    private val contacts = mutableListOf<Contact>()

    init {
        loadContactsFromDatabase()
    }

    private fun loadContactsFromDatabase() {
        try {
            File(databaseFileName).useLines { lines ->
                contacts.addAll(lines.mapNotNull {
                    val values = it.split(",")
                    if (values.size == 4) {
                        Contact(values[0], values[1], values[2], values[3])
                    } else {
                        null
                    }
                })
            }
        } catch (e: FileNotFoundException) {
            println("Database file not found. Creating a new file.")
        } catch (e: Exception) {
            println("Error loading contacts from the database: ${e.message}")
        }
    }

    private fun saveContactsToDatabase() {
        try {
            PrintWriter(databaseFileName).use { writer ->
                contacts.forEach { contact ->
                    writer.println("${contact.name},${contact.phoneNumber},${contact.email},${contact.address}")
                }
                println("${Color.GREEN}Database updated successfully.${Color.RESET}")
            }
        } catch (e: Exception) {
            println("${Color.RED}Error saving contacts to the database: ${e.message}${Color.RESET}")
        }
    }

    fun addContact(name: String, phoneNumber: String, email: String, address: String) {
        val newContact = Contact(name, phoneNumber, email, address)
        contacts.add(newContact)
        saveContactsToDatabase()
        println("${Color.GREEN}Contact added successfully.${Color.RESET}")
    }

    fun searchContact(name: String): Contact? {
        return contacts.find { it.name == name }
    }

    fun editContact(name: String, phoneNumber: String, email: String, address: String) {
        val existingContact = searchContact(name)
        if (existingContact != null) {
            contacts.remove(existingContact)
            val updatedContact = Contact(name, phoneNumber, email, address)
            contacts.add(updatedContact)
            saveContactsToDatabase()
            println("${Color.GREEN}Contact edited successfully.${Color.RESET}")
        } else {
            println("${Color.RED}Contact not found.${Color.RESET}")
        }
    }

    fun deleteContact(name: String) {
        val existingContact = searchContact(name)
        if (existingContact != null) {
            contacts.remove(existingContact)
            saveContactsToDatabase()
            println("${Color.GREEN}Contact deleted successfully.${Color.RESET}")
        } else {
            println("${Color.RED}Contact not found.${Color.RESET}")
        }
    }

    fun listAllContacts() {
        if (contacts.isEmpty()) {
            println("${Color.YELLOW}Phone book is empty.${Color.RESET}")
        } else {
            println("${Color.BLUE}All contacts in the phone book:${Color.RESET}")
            contacts.forEach { contact ->
                println("${Color.CYAN}Name: ${contact.name}, Phone Number: ${contact.phoneNumber}, Email: ${contact.email}, Address: ${contact.address}${Color.RESET}")
            }
        }
    }

    fun backupContactsToFile(fileName: String) {
        try {
            PrintWriter(fileName).use { writer ->
                contacts.forEach { contact ->
                    writer.println("${contact.name},${contact.phoneNumber},${contact.email},${contact.address}")
                }
                println("${Color.GREEN}Backup successful. Contacts exported to $fileName.${Color.RESET}")
            }
        } catch (e: Exception) {
            println("${Color.RED}Error: ${e.message}${Color.RESET}")
        }
    }

    fun restoreContactsFromFile(fileName: String) {
        try {
            File(fileName).useLines { lines ->
                contacts.clear()
                contacts.addAll(lines.mapNotNull {
                    val values = it.split(",")
                    if (values.size == 4) {
                        Contact(values[0], values[1], values[2], values[3])
                    } else {
                        null
                    }
                })
                saveContactsToDatabase()
                println("${Color.GREEN}Restore successful. Contacts imported from $fileName.${Color.RESET}")
            }
        } catch (e: FileNotFoundException) {
            println("${Color.RED}Error: File $fileName not found.${Color.RESET}")
        } catch (e: Exception) {
            println("${Color.RED}Error: ${e.message}${Color.RESET}")
        }
    }

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.matches(Regex("\\d{11}"))
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }

    fun isValidInput(input: String?): Boolean {
        return !input.isNullOrBlank()
    }
}

object Color {
    const val RESET = "\u001B[0m"
    const val BLACK = "\u001B[30m"
    const val RED = "\u001B[31m"
    const val GREEN = "\u001B[32m"
    const val YELLOW = "\u001B[33m"
    const val BLUE = "\u001B[34m"
    const val CYAN = "\u001B[36m"
}

fun main() {
    val phoneBook = PhoneBook("phonebook_database.txt")

    while (true) {
        println("\n${Color.BLUE}1. Add Contact")
        println("2. Search Contact")
        println("3. Edit Contact")
        println("4. Delete Contact")
        println("5. List All Contacts")
        println("6. Backup Contacts")
        println("7. Restore Backup")
        println("8. Exit${Color.RESET}")
        print("${Color.GREEN}Enter your choice: ${Color.RESET}")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("${Color.BLUE}Enter Name: ${Color.RESET}")
                val name = readLine() ?: ""
                print("${Color.BLUE}Enter Phone Number: ${Color.RESET}")
                val phoneNumber = readLine() ?: ""
                print("${Color.BLUE}Enter Email: ${Color.RESET}")
                val email = readLine() ?: ""
                print("${Color.BLUE}Enter Address: ${Color.RESET}")
                val address = readLine() ?: ""

                if (phoneBook.isValidInput(name) &&
                    phoneBook.isValidPhoneNumber(phoneNumber) &&
                    phoneBook.isValidEmail(email) &&
                    phoneBook.isValidInput(address)
                ) {
                    phoneBook.addContact(name, phoneNumber, email, address)
                } else {
                    println("${Color.RED}Invalid input. Please check your name, phone number, email, and address.${Color.RESET}")
                }
            }
            2 -> {
                print("${Color.BLUE}Enter Name to Search: ${Color.RESET}")
                val name = readLine() ?: ""
                val contact = phoneBook.searchContact(name)
                if (contact != null) {
                    println("${Color.GREEN}Contact Found:${Color.RESET}")
                    println("${Color.CYAN}Name: ${contact.name}, Phone Number: ${contact.phoneNumber}, Email: ${contact.email}, Address: ${contact.address}${Color.RESET}")
                } else {
                    println("${Color.RED}Contact not found.${Color.RESET}")
                }
            }
            3 -> {
                print("${Color.BLUE}Enter Name to Edit: ${Color.RESET}")
                val editName = readLine() ?: ""
                if (phoneBook.isValidInput(editName)) {
                    val existingContact = phoneBook.searchContact(editName)
                    if (existingContact != null) {
                        println("${Color.BLUE}Enter new details for the contact:${Color.RESET}")
                        print("${Color.BLUE}\tEnter Phone Number: ${Color.RESET}")
                        val newPhoneNumber = readLine() ?: ""
                        print("${Color.BLUE}\tEnter Email: ${Color.RESET}")
                        val newEmail = readLine() ?: ""
                        print("${Color.BLUE}\tEnter Address: ${Color.RESET}")
                        val newAddress = readLine() ?: ""

                        // validate email and phone
                        if (phoneBook.isValidPhoneNumber(newPhoneNumber) &&
                            phoneBook.isValidEmail(newEmail) &&
                            phoneBook.isValidInput(newAddress)
                        ) {
                            phoneBook.editContact(editName, newPhoneNumber, newEmail, newAddress)
                            println("${Color.GREEN}Contact $editName edited successfully.${Color.RESET}")
                        } else {
                            println("${Color.RED}Invalid input. Please check your name, phone number, email, and address.${Color.RESET}")
                        }
                    } else {
                        println("${Color.RED}Contact not found.${Color.RESET}")
                    }
                } else {
                    println("${Color.RED}Invalid input. Please enter a valid name.${Color.RESET}")
                }
            }
            4 -> {
                print("${Color.BLUE}Enter Name to Delete: ${Color.RESET}")
                val deleteName = readLine() ?: ""
                if (phoneBook.isValidInput(deleteName)) {
                    phoneBook.deleteContact(deleteName)
                } else {
                    println("${Color.RED}Invalid input. Please enter a valid name.${Color.RESET}")
                }
            }
            5 -> phoneBook.listAllContacts()
            6 -> {
                print("${Color.BLUE}Enter backup file name: ${Color.RESET}")
                val backupFileName = readLine() ?: ""
                phoneBook.backupContactsToFile(backupFileName)
            }
            7 -> {
                print("${Color.BLUE}Enter restore file name: ${Color.RESET}")
                val restoreFileName = readLine() ?: ""
                phoneBook.restoreContactsFromFile(restoreFileName)
            }
            8 -> {
                println("${Color.YELLOW}Exiting the Phone Book.${Color.RESET}")
                return
            }
            else -> println("${Color.RED}Invalid choice. Please enter a valid option.${Color.RESET}")
        }
    }
}
