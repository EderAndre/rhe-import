package br.rs.gov.defensoria.rhe.utils

import org.springframework.stereotype.Component

@Component
class Type {
    String name

    String expressionFromDetection

    String extension

    Integer [] columnLimits

    String[] columnLabels
}
