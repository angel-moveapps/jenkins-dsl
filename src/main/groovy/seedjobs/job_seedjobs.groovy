import groovy.io.FileType

def list = []

def currentDir = new File(".").getAbsolutePath()
println currentDir

println new File(getClass().protectionDomain.codeSource.location.path).parent

println getClass().protectionDomain.codeSource.location.path

def dir = new File(getClass().protectionDomain.codeSource.location.path)
dir.eachFileRecurse (FileType.FILES) { file ->
  list << file
}

list.each {
  println it.path
}

seedjobs = [
        [name: 'Infraestructura', external: 'src/main/groovy/projects/Infraestructura/*.groovy']
]

seedjobs.each { seedjob ->

    def jobName = seedjob.name + '/' +'job_seedjob'

    job(jobName) {
        scm {
            git {
                remote {
                    url('https://github.com/angel-moveapps/jenkins-dsl.git')
                }
                branch('master')
                extensions {
                    cleanBeforeCheckout()
                    cloneOptions {
                        shallow(true)
                    }
                }
            }
        }
        wrappers {
            timestamps()
        }
        steps {
            dsl{
                external(seedjob.external)
                additionalClasspath('src/main/groovy/dsl-helpers')
            }
        }
    }
    queue(jobName)
}
