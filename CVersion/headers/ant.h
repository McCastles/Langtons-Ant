#ifndef _ANT_H_
#define _ANT_H_

#include "board.h"
typedef enum {UP, RIGHT, DOWN, LEFT} pos;


typedef struct e
{
	int x_coord;
	int y_coord;
	pos ant_pos;
	int isAlive;

} *ant_t; 

ant_t createAnt(int, int, char*, char*, char*, char*, char*);
void antStep (ant_t proto, board_t b);
void move(ant_t proto, board_t b);
void turnRight(ant_t proto);
void turnLeft(ant_t proto);

#endif
