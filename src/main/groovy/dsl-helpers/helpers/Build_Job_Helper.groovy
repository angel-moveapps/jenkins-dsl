package helpers

class Build_Job_Helper {

    static void general_config(job) {
        job.with {
            authorization{
                permission('hudson.model.Item.Workspace:authenticated')//configuración de seguridad para el proyecto
            }
            logRotator {
                numToKeep(10)
                artifactNumToKeep(10)
            }
        }

    }

    static void general_config(job, jobDescription) {
        job.with {
            authorization {
                blocksInheritance(false)
            }
            description(jobDescription)
            
        }
    }

    static void addGitParameter_branch(def job, String type_branch, String sortMode_branch, String useRepository_branch, String filter_branch, String name_branch) {
        job.with {
            configure { project ->
                project / 'properties' / 'hudson.model.ParametersDefinitionProperty' / parameterDefinitions <<
                        'net.uaznia.lukanus.hudson.plugins.gitparameter.GitParameterDefinition' {
                            name(name_branch)
                            description(name_branch)
                            type(type_branch)
                            tagFilter(filter_branch)
                            branchFilter('origin/master')
                            sortMode(sortMode_branch)
                            selectedValue('origin/master')
                            defaultValue('origin/master')
                            useRepository(useRepository_branch)
                            quickFilterEnabled(false)
                            listSize('1')
                        }
            }
        }
    }

    static void addGitParameter_branch_prod(def job, String type_branch, String sortMode_branch, String useRepository_branch, String filter_branch, String name_branch) {
        job.with {
            configure { project ->
                project / 'properties' / 'hudson.model.ParametersDefinitionProperty' / parameterDefinitions <<
                        'net.uaznia.lukanus.hudson.plugins.gitparameter.GitParameterDefinition' {
                            name(name_branch)
                            description(name_branch)
                            type(type_branch)
                            tagFilter(filter_branch)
                            branchFilter('origin/release')
                            sortMode(sortMode_branch)
                            selectedValue('origin/release')
                            defaultValue('origin/release')
                            useRepository(useRepository_branch)
                            quickFilterEnabled(false)
                            listSize('1')
                        }
            }
        }
    }

    static void stringParameters(job) {

        job.with {

            parameters {
                stringParam('string1', '', 'variable string input para jobs')
                stringParam('string2', '', 'variable string input para jobs')
                stringParam('string3', '', 'variable string input para jobs')
                stringParam('string4', '', 'variable string input para jobs')

            }
        }
    }

    static void ecd_application_file(job) {

        job.with {

            parameters {
                stringParam('FilesCM', 'application.yml', 'Ingresa el Nombre del archivo de parametros gradle para env vars')
            }
        }
    }

    static void general_variables(job, String repo_name) {
        job.with {
            environmentVariables {
                env("REPO_NAME", "${repo_name}")
                keepBuildVariables(true)
            }
        }
    }


// Aqui se agregan los nuevos parametros a utilizar en los jobs


}