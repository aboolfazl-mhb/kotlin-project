import java.io.File
import java.io.FileNotFoundException
import java.io.PrintWriter
import java.util.*

data class Contact(val id: UUID, val firstName: String,val lastName: String, val phoneNumber: String, val email: String, val address: String)

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
                    if (values.size == 5) {
                        Contact(UUID.fromString(values[0]), values[1], values[2], values[3], values[4],values[5])
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
                    writer.println("${contact.id},${contact.firstName},${contact.lastName},${contact.phoneNumber},${contact.email},${contact.address}")
                }
                println("${Color.GREEN}Database updated successfully.${Color.RESET}")
            }
        } catch (e: Exception) {
            println("${Color.RED}Error saving contacts to the database: ${e.message}${Color.RESET}")
        }
    }

    fun addContact(firstName: String, lastName: String, phoneNumber: String, email: String, address: String) {
        val newContact = Contact(UUID.randomUUID(), firstName,lastName, phoneNumber, email, address)
        contacts.add(newContact)
        saveContactsToDatabase()
        println("${Color.GREEN}Contact added successfully.${Color.RESET}")
    }

    fun searchContact(query: String): Contact? {
        return contacts.find {
            it.firstName.equals(query, ignoreCase = true) || it.phoneNumber == query
        }
    }

    fun editContact(id: UUID,firstName: String, lastName: String, phoneNumber: String, email: String, address: String) {
        val existingContact = contacts.find { it.id == id }
        if (existingContact != null) {
            contacts.remove(existingContact)
            val updatedContact = Contact(id, firstName , lastName , phoneNumber, email, address)
            contacts.add(updatedContact)
            saveContactsToDatabase()
            println("${Color.GREEN}Contact edited successfully.${Color.RESET}")
        } else {
            println("${Color.RED}Contact not found.${Color.RESET}")
        }
    }

    fun deleteContact(phone: String) {
        val existingContact = searchContact(phone)
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
            println("${Color.BLUE}All contacts in the phone book (sorted by first name):${Color.RESET}")
            contacts.sortedBy { it.firstName }.forEach { contact ->
                println("${Color.CYAN}firstName: ${contact.firstName}, lastName: ${contact.lastName}, " +
                        "Phone Number: ${contact.phoneNumber}, Email: ${contact.email}, Address: ${contact.address}${Color.RESET}")
            }
        }
    }

    fun backupContactsToFile(fileName: String) {
        try {
            PrintWriter(fileName).use { writer ->
                contacts.forEach { contact ->
                    writer.println("${contact.id},${contact.firstName},${contact.lastName},${contact.phoneNumber},${contact.email},${contact.address}")
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
                    if (values.size == 6) {
                        Contact(UUID.fromString(values[0]), values[1], values[2], values[3], values[4], values[5])
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
    

    private fun isDuplicateId(id: UUID): Boolean {
        return contacts.any { it.id == id }
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
                print("${Color.BLUE}Enter firstName: ${Color.RESET}")
                val firstName = readLine() ?: ""
                print("${Color.BLUE}Enter lastname: ${Color.RESET}")
                val lastname = readLine() ?: ""
                print("${Color.BLUE}Enter Phone Number: ${Color.RESET}")
                val phoneNumber = readLine() ?: ""
                print("${Color.BLUE}Enter Email: ${Color.RESET}")
                val email = readLine() ?: ""
                print("${Color.BLUE}Enter Address: ${Color.RESET}")
                val address = readLine() ?: ""

                if (phoneBook.isValidInput(firstName) &&
                    phoneBook.isValidInput(lastname) &&
                    phoneBook.isValidPhoneNumber(phoneNumber) &&
                    phoneBook.isValidEmail(email) &&
                    phoneBook.isValidInput(address)
                ) {
                    phoneBook.addContact(firstName,lastname, phoneNumber, email, address)
                } else {
                    println("${Color.RED}Invalid input. Please check your firstName, phone number, email, and address.${Color.RESET}")
                }
            }
            2 -> {
                print("${Color.BLUE}Enter firstName or phone to Search: ${Color.RESET}")
                val query = readLine() ?: ""
                val contact = phoneBook.searchContact(query)
                if (contact != null) {
                    println("${Color.GREEN}Contact Found:${Color.RESET}")
                    println("${Color.CYAN}firstName: ${contact.firstName}, lastName: ${contact.lastName}, Phone Number: ${contact.phoneNumber}, Email: ${contact.email}, Address: ${contact.address}${Color.RESET}")
                } else {
                    println("${Color.RED}Contact not found.${Color.RESET}")
                }
            }
            3 -> {
                print("${Color.BLUE}Enter phone to Edit: ${Color.RESET}")
                val phone = readLine() ?: ""
                if (phoneBook.isValidInput(phone)) {
                    val existingContact = phoneBook.searchContact(phone)
                    if (existingContact != null) {
                        println("${Color.BLUE}Enter new details for the contact:${Color.RESET}")
                        print("${Color.BLUE}\tEnter firstName : ${Color.RESET}")
                        val newfirstName = readLine() ?: ""
                        print("${Color.BLUE}\tEnter lastName : ${Color.RESET}")
                        val newlastName = readLine() ?: ""
                        print("${Color.BLUE}\tEnter Phone Number: ${Color.RESET}")
                        val newPhoneNumber = readLine() ?: ""
                        print("${Color.BLUE}\tEnter Email: ${Color.RESET}")
                        val newEmail = readLine() ?: ""
                        print("${Color.BLUE}\tEnter Address: ${Color.RESET}")
                        val newAddress = readLine() ?: ""

                        // validate email and phone
                        if (phoneBook.isValidPhoneNumber(newfirstName) &&
                            phoneBook.isValidPhoneNumber(newlastName) &&
                            phoneBook.isValidPhoneNumber(newPhoneNumber) &&
                            phoneBook.isValidEmail(newEmail) &&
                            phoneBook.isValidInput(newAddress)
                        ) {
                            phoneBook.editContact(existingContact.id,newfirstName,newlastName,newPhoneNumber, newEmail, newAddress)
                            println("${Color.GREEN}Contact $phone edited successfully.${Color.RESET}")
                        } else {
                            println("${Color.RED}Invalid input. Please check your firstname, lastname, phone number, email, and address.${Color.RESET}")
                        }
                    } else {
                        println("${Color.RED}Contact not found.${Color.RESET}")
                    }
                } else {
                    println("${Color.RED}Invalid input. Please enter a valid name.${Color.RESET}")
                }
            }
            4 -> {
                print("${Color.BLUE}Enter Phone to Delete: ${Color.RESET}")
                val deletePhone = readLine() ?: ""
                if (phoneBook.isValidInput(deletePhone)) {
                    phoneBook.deleteContact(deletePhone)
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
