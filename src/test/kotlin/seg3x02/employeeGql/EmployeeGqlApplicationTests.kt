package seg3x02.employeeGql

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeeRepository

import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput
import seg3x02.employeeGql.resolvers.EmployeesController
import org.springframework.data.mongodb.core.MongoOperations

@SpringBootTest
class EmployeeGqlApplicationTests @Autowired constructor(
    val mongoOperations: MongoOperations
) {

	@Test
	fun contextLoads() {
	}

	@Autowired
    lateinit var employeeRepository: EmployeeRepository

	@Test
    fun `test the initialization of the employee entity`() {
        val employee = Employee("John Smith", "2024-11-20", "Ottawa", 1000.0f, "M", "another@email.com")

        Assertions.assertEquals("John Smith", employee.name)
    }

    @Test
    fun `test the save and find functions of the employee repository`() {
        val employee = Employee("John Smith", "2024-11-20", "Ottawa", 1000.0f, "M", "another@email.com")
        val employeeId = employeeRepository.save(employee).employeeId

        val repoEmployee = employeeRepository.findById(employeeId)
        Assertions.assertTrue(repoEmployee.isPresent)
    }

	@Test
    fun `test the write operation to the database`() {
        val employee = CreateEmployeeInput("John Smith", "2024-11-20", "Ottawa", 1000.0f, "M", "another@email.com")
		var employeeController = EmployeesController(mongoOperations, employeeRepository)

        val dbEmployee = employeeController.newEmployee(employee)

        Assertions.assertTrue(employeeController.employees().contains(dbEmployee))
    }

}
