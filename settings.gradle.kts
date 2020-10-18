rootProject.name = "venexer"

include("services:auth-service:auth-client")
include("services:auth-service:auth-server")

include("services:config-server")

include("services:service-registry-server")

include("services:gateway-service")

include("services:account-service:account-server")
include("services:account-service:account-client")

include("services:question-service:question-server")
include("services:question-service:question-client")

include("utils")