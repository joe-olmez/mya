export class CurrencyRate {
  id!: number;
  date!: Date;
  amount: number = 1.0;
  baseCode = Code.USD;
  cad!: number;
  eur!: number;
  gbp!: number;
  jpy!: number;
  tryy!: number;
  usd!: number;
}

export enum Code {
  CAD = 'CAD',
  EUR = 'EUR',
  GBP = 'GBP',
  JPY = 'JPY',
  TRY = 'TRY',
  USD = 'USD',
}
