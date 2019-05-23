import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const LineNotas = (props) => {
    const { C1, C2, C3, C4 } = props;

    return (
        <View style={[styles.line]}>
            <View style={styles.viewNota}>
                <Text style={[styles.nota, C1 === "I" ? styles.insuficiente : null, C1 === "?" ? styles.indefinido : null]}>{C1}</Text>
            </View>
            

            <View style={styles.viewNota}>
                <Text style={[styles.nota, C2 === "I" ? styles.insuficiente : null, C2 === "?" ? styles.indefinido : null]}>{C2}</Text>
            </View>
                

            <View style={styles.viewNota}>
                <Text style={[styles.nota, C3 === "I" ? styles.insuficiente : null, C3 === "?" ? styles.indefinido : null]}>{C3}</Text>
            </View>

            <View style={styles.viewNota}>
                <Text style={[styles.nota, C4 === "I" ? styles.insuficiente : null, C4 === "?" ? styles.indefinido : null]}>{C4}</Text>
            </View>
        </View>
    );

};

const styles = StyleSheet.create({
    line: {
        flexDirection: 'row',
        paddingTop: 3,
        paddingBottom: 3,
        paddingLeft: 10,
        paddingRight: 10,
        justifyContent: 'center',
        marginBottom: 10
    },

    viewNota: {
        paddingLeft: 20,
        paddingRight: 20,
        alignItems: 'center',
        flex: 1
    },

    nota: {
        fontSize: 18,
        color: '#2ce832',
    },

    insuficiente: {
      color: '#d63324'
    },

    indefinido: {
        color: '#f4f442'
    }
});

export default LineNotas;