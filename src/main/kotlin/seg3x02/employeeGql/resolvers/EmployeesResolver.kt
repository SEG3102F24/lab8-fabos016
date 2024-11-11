package seg3x02.employeeGql.resolvers

import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeeRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput
import java.util.UUID

@Controller
class EmployeesController(val mongoOperations: MongoOperations,
                        private val employeeRepository: EmployeeRepository
) {
    @QueryMapping
    fun employees(): List<Employee> {
        return employeeRepository.findAll()
    }

    // @QueryMapping
    // fun employees(@Argument employeeId: String): List<Employee> {
    //     val query = Query()
    //     query.addCriteria(Criteria.where("employeeId").`is`(employeeId))
    //     return mongoOperations.find(query, Employee::class.java)
    // }


    @MutationMapping
    fun newEmployee(@Argument("createEmployeeInput") input: CreateEmployeeInput) : Employee {
        val employee = Employee(input.name, input.dateOfBirth, input.city, input.salary, input.gender, input.email)
        employee.employeeId = UUID.randomUUID().toString()
        employeeRepository.save(employee)
        return employee
    }
}