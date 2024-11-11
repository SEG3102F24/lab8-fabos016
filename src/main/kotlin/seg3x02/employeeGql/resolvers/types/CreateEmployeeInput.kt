package seg3x02.employeeGql.resolvers.types

class CreateEmployeeInput (
    val name: String = "",
    val dateOfBirth: String  = "",
    val city: String = "",
    val salary: Float = 0.0f,
    val gender: String = "",
    val email: String  = ""
)

