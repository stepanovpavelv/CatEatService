FROM node:alpine3.16 as build
WORKDIR /usr/src/app

EXPOSE 80

COPY package.json package-lock.json ./
RUN npm install

COPY . .
RUN npm run build:prod

FROM nginx:1.19.10-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/cat-monitoring /usr/share/nginx/html