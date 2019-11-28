# ----------------------------#
#        JAVA BUILD           #
# ----------------------------#
FROM maven:3.6-jdk-11 AS build

COPY pom.xml .

# Expected to download dependencies but not build
RUN mvn -Dmaven.repo.local=$MVN_LOCAL_REPO dependency:go-offline

# Now copy actual source, i.e., this line should be after downloading dependencies to avoid repeated download when src changes
COPY src src

# Build now
RUN mvn -Dmaven.repo.local=$MVN_LOCAL_REPO -f pom.xml clean package -DskipTests

# -------------------------------#
#        JAVA Service            #
# -------------------------------#
FROM openjdk:11

EXPOSE 8980

# Copy the built service jars and resources
COPY --from=build /target/CloudConfigServer.jar config.jar

ENTRYPOINT ["java","-jar","config.jar"]
