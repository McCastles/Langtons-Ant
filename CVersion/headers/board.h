#ifndef _BOARD_H_
#define _BOARD_H_

typedef struct 
{
	int w;
	int h;
	int** board;

} *board_t;

board_t createBoard(int, int);
void printBoard(board_t);

#endif
