export class CurrencyData {
  date: any;
  amount: any;
  baseCode!: Code;
  aud: any;
  cad: any;
  chf: any;
  eur: any;
  gbp: any;
  jpy: any;
  rub: any;
  try: any;
  usd: any;
}

export enum Code {
  AUD = 'AUD',
  CAD = 'CAD',
  CHF = 'CHF',
  EUR = 'EUR',
  GBP = 'GBP',
  USD = 'USD',
  JPY = 'JPY',
  RUB = 'RUB',
  TRY = 'TRY',
}

// Use "!"" for no initializer
// export class CurrencyData {
//   id!: number;
//   date!: Date;
//   amount!: number;
//   baseCode!: Code;
//   aUD!: number;
//   cAD!: number;
//   cHF!: number;
//   eUR!: number;
//   gBP!: number;
//   uSD!: number;
//   jPY!: number;
//   rUB!: number;
//   tRY!: number;
// }

// OR create an interface as below
// export interface CurrencyData {
//   id: number;
//   date: Date;
//   amount: number;
//   baseCode: Code;
//   aUD: number;
//   cAD: number;
//   cHF: number;
//   eUR: number;
//   gBP: number;
//   uSD: number;
//   jPY: number;
//   rUB: number;
//   tRY: number;
// }
