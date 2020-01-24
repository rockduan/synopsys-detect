package com.synopsys.integration.configuration.config

fun emptyConfig(): PropertyConfiguration {
    return PropertyConfiguration(emptyList())
}

fun configOf(vararg properties: Pair<String, String>): PropertyConfiguration {
    val propertySource = propertySourceOf("map", *properties)
    return configOf(propertySource)
}

fun propertySourceOf(name: String, vararg properties: Pair<String, String>): PropertySource {
    return MapPropertySource(name, properties.toMap())
}

fun configOf(vararg propertySources: PropertySource): PropertyConfiguration {
    return PropertyConfiguration(propertySources.toList())
}