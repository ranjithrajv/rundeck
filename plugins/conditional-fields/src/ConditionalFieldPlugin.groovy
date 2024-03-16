import com.dtolabs.rundeck.core.execution.workflow.StepExecutionListener
import com.dtolabs.rundeck.plugins.Plugin

rundeckPlugin(StepExecutionListener) {
  setName("Conditional Field Plugin")
  setDescription("Allows defining conditions to show/hide/disable fields in Rundeck forms")

  // Replace with actual Rundeck API access (implementation needed)
  def conditionalFieldService = new ConditionalFieldService() 

  beforeExecution { executionContext, nodeExecutionContext, definition ->
    // Get conditional field definitions from job definition (implementation needed)
    List<ConditionalFieldDefinition> definitions = getConditionalFieldDefinitions(definition)
    conditionalFieldService.registerConditionalFields(definition.getName(), definitions)
  }

  afterExecution { executionContext, nodeExecutionContext, definition, result ->
    Map<String, String> formData = executionContext.getData()
    conditionalFieldService.evaluateConditions(formData, getConditionalFieldDefinitions(definition))
  }
}
