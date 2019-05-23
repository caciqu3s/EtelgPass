import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { Card, Divider } from 'react-native-elements';
import LineFaltas from './LineFaltas';
import LineNotas from './LineNotas';

const CardNotas = (props) => {
    const { notas } = props;

    return (
        <Card title={notas.disciplina} containerStyle={styles.container} dividerStyle={styles.divider}>
            <LineNotas C1={notas.conceito1} C2={notas.conceito2} C3={notas.conceito3} C4={notas.conceito4}/>
            <Divider style={styles.divider} />
            <LineFaltas content={notas.porcentagem} />
        </Card>    
    )
};

const styles = StyleSheet.create({
    container: {
        borderRadius: 10
    },

    divider: {
        backgroundColor: '#00c667'
    }
});

// #bce78a
export default CardNotas;